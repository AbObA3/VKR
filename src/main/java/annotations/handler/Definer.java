package annotations.handler;

import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import connection.impl.ConnectionImpl;
import exceptions.AnnotationNotFoundException;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.logging.Logger;

@NoArgsConstructor
public class Definer {
    private static final Logger log;

    static {
        log = Logger.getLogger(ConnectionImpl.class.getName());
    }
    private void checkIfTable(Object object) {
        if (Objects.isNull(object)) {
            throw new NullPointerException("Не создан экземпляр класса");
        }
        var clazz = object.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new AnnotationNotFoundException("Класс "
                    + clazz.getSimpleName()
                    + " без аннотации Table");
        }
    }

    public String getTableName(Object object) {
        try {
            checkIfTable(object);
            var table = object.getClass().getAnnotation(Table.class);
            return table.tableName();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return null;
    }

    public String getSchemaName(Object object) {
        try {
            checkIfTable(object);
            var table = object.getClass().getAnnotation(Table.class);
            return table.schemaName();
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return null;
    }

    public Field getPrimaryKey(Object object) {
        var fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(PrimaryKey.class)){
                return field;
            }
        }
        return null;
    }


    public Field getForeignKey(Object object) {
        var fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(ForeignKey.class)){
                return field;
            }
        }
        return null;
    }

    public boolean isPrimaryKey(Field field) {
        return field.isAnnotationPresent(PrimaryKey.class);
    }


    public boolean isForeignKey(Field field) {
        return field.isAnnotationPresent(ForeignKey.class);
    }
}
