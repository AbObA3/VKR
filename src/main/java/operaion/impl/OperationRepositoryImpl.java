package operaion.impl;

import annotations.handler.Definer;
import connection.Connection;
import connection.impl.ConnectionImpl;
import operaion.OperationRepository;
import split.Splitter;
import split.impl.SplitterImpl;
import utils.enums.TypesEnum;
import validator.Validator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class OperationRepositoryImpl<T> implements OperationRepository<T> {


    private final Constructor<T> ctor;
    private final Connection connection;
    private final Splitter splitter;

    private Map<String, Map<String, String>> tablesXML;

    public OperationRepositoryImpl(Class<T> data) throws NoSuchMethodException {
        this.ctor = data.getConstructor();
        this.splitter = new SplitterImpl();
        this.connection = new ConnectionImpl();
        this.tablesXML = new HashMap<>();
    }

    /**
     * @return
     */
    @Override
    public List<T> selectAll() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        var newInstance = ctor.newInstance();
        var schemaName = Definer.getSchemaName(newInstance);
        var tableName = Definer.getTableName(newInstance);
        List<T> list = new ArrayList<>();
        splitter.getRows(connection.getQueryResult(schemaName, tableName))
                .forEach(s -> {
                    try {
                        T temp = ctor.newInstance();
                        list.add((T) getObject(s, temp));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e.getMessage());
                    }

                });


        return list;
    }

    private Object getObject(String s, Object object) {

        Arrays.stream(object.getClass().getDeclaredFields()).parallel().forEach(field -> {
            try {
                field.trySetAccessible();
                if (Definer.isForeignKey(field)) {
                    Object foreignTemp = field.getType().getConstructor().newInstance();
                    var idName = Definer.getPrimaryKey(foreignTemp).getName();
                    var idValue = splitter.getData(s, Validator.fromCamelToSnake(field.getName()));
                    var tableName = Definer.getTableName(foreignTemp);
                    var schemaName = Definer.getSchemaName(foreignTemp);
                    var foreignXml = getMap(tableName, schemaName, idName).get(idValue);
                    field.set(object, getObject(foreignXml, field.getType().getConstructor().newInstance()));
                } else {
                    field.set(object, TypesEnum
                            .valueOf(field
                                    .getType()
                                    .getSimpleName()
                                    .toUpperCase())
                            .getCastedData(splitter.getData(s, Validator
                                    .fromCamelToSnake(field.getName()))));
                }
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        return object;

    }

    Map<String, String> getMap(String tableName, String schemaValue, String tag) {

        if (!tablesXML.containsKey(tableName)) {
            tablesXML.put(tableName, splitter.getMapRows(tag, connection.getQueryResult(schemaValue, tableName)));
        }
        return tablesXML.get(tableName);
    }


}
