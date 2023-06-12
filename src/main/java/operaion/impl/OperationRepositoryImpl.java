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
import java.util.ArrayList;
import java.util.List;


public class OperationRepositoryImpl<T> implements OperationRepository<T> {


    private final Constructor<T> ctor;
    private final Connection connection;
    private final Splitter splitter;

    public OperationRepositoryImpl(Class<T> data) throws NoSuchMethodException {
        this.ctor = data.getConstructor();
        this.splitter = new SplitterImpl();
        this.connection = new ConnectionImpl();
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
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                             NoSuchMethodException e) {
                        throw new RuntimeException(e.getMessage());
                    }

                });


        return list;
    }

    private Object getObject(String s, Object object) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        var temp = object.getClass().getConstructor().newInstance();
        for (var field : temp.getClass().getDeclaredFields()) {
            field.trySetAccessible();
            if (Definer.isForeignKey(field)) {
                var foreignTemp = field.getType().getConstructor().newInstance();
                var idName = Definer.getPrimaryKey(foreignTemp).getName();
                var idValue = getData(s, Validator.fromCamelToSnake(field.getName()));
                var tableName = Definer.getTableName(foreignTemp);
                var schemaName = Definer.getSchemaName(foreignTemp);
                field.set(temp, getObject(connection.getForeignQueryResult(schemaName, tableName, idName, idValue), field.getType().getConstructor().newInstance()));

            } else {
                field.set(temp, TypesEnum
                        .valueOf(field
                                .getType()
                                .getSimpleName()
                                .toUpperCase())
                        .getCastedData(getData(s, Validator
                                .fromCamelToSnake(field.getName()))));
            }
        }
        return temp;

    }


    private String getData(String s, String tag) {

        return s
                .substring(
                        s.indexOf(String.format("<%s>", tag)) + tag.length() + 2,
                        s.indexOf(String.format("</%s>", tag)));
    }
}
