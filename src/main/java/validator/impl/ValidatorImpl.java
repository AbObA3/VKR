package validator.impl;

import annotations.Table;
import annotations.handler.Definer;
import connection.impl.ConnectionImpl;
import exceptions.NotFoundPrimaryKeyException;
import exceptions.NotMatchedNamesException;
import org.example.schemagen.Row;
import org.example.schemagen.SchemaGenerator;
import org.reflections.Reflections;
import utils.enums.TypesEnum;
import validator.Validator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class ValidatorImpl implements Validator {

    private static final Logger log;

    static {
        log = Logger.getLogger(ConnectionImpl.class.getName());
    }

    @Override
    public void validate(Map<String, File> xsdMap, Integer quantityTables) {
        Definer definer = new Definer();
        Map<String,File> xmlMap = new HashMap<>();
        SchemaFactory factory =
                SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        SchemaGenerator generator = new SchemaGenerator();
        try {
            Reflections reflections = new Reflections("tables");
            var annotated = new ArrayList<>(reflections.get(SubTypes.of(TypesAnnotated.with(Table.class)).asClass()));
            if (!quantityTables.equals(annotated.size())) {
                throw new NotMatchedNamesException(String.format("Количество таблиц в реляционной(%d) и обьектной(%d) схемах не совпадает", quantityTables, annotated.size()));
            }
            for (Class<?> aClass : annotated) {
                var object = aClass.getConstructor().newInstance();
                var className = aClass.getSimpleName();
                var tableName = definer.getTableName(object);
                if (!className.equalsIgnoreCase(tableName.replace("_", ""))) {
                    throw new NotMatchedNamesException(String.format("Имя класса %s не совпадает с именем таблицы в бд %s", className, tableName));
                }
                for (var field : aClass.getDeclaredFields()) {
                    Row row = new Row();
                    Field foreignField = null;
                    if (definer.isPrimaryKey(field)) {
                        row.setConstraintType("p");
                    } else if (definer.isForeignKey(field)) {
                        row.setConstraintType("f");
                        foreignField = definer.getPrimaryKey(field.getType().getConstructor().newInstance());
                        if (Objects.isNull(foreignField)) {
                            throw new NotFoundPrimaryKeyException(String.format("В таблице %s присутствует вторичный ключ %s, но в таблице %s отстутствует первичный ключ"
                                    , aClass.getSimpleName(), field.getName(), field.getType().getSimpleName()));
                        }
                        row.setReferenceTable(fromCamelToSnake(field.getType().getSimpleName()));
                        row.setReferenceCol(fromCamelToSnake(foreignField.getName()));
                        row.setDataType(TypesEnum.valueOf(foreignField.getType().getSimpleName().toUpperCase()).getPostgresType());
                        row.setNumericPrecision(TypesEnum.valueOf(foreignField.getType().getSimpleName().toUpperCase()).getNumericPrecision());
                    } else {
                        row.setConstraintType("c");
                    }
                    row.setTableSchema(definer.getSchemaName(object));
                    row.setTableName(fromCamelToSnake(aClass.getSimpleName()));
                    row.setColumnName(fromCamelToSnake(field.getName()));
                    if (Objects.isNull(foreignField)) {
                        row.setDataType(TypesEnum.valueOf(field.getType().getSimpleName().toUpperCase()).getPostgresType());
                        row.setNumericPrecision(TypesEnum.valueOf(field.getType().getSimpleName().toUpperCase()).getNumericPrecision());
                    }
                    Map.Entry<String, File> entry = generator.genSchemaXML(row);
                    xmlMap.put(entry.getKey(),entry.getValue());
                    var xsdFile = xsdMap.get(entry.getKey());
                    if (Objects.isNull(xsdFile)) {
                        throw new FileNotFoundException(String.format("Не найдена схема валидации для файла %s", entry.getKey()));
                    }
                    Schema schema = factory.newSchema(xsdFile);
                    javax.xml.validation.Validator validator = schema.newValidator();
                    validator.validate(new StreamSource(entry.getValue()));
                }
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
            log.info("Обьектная схема не валидна реляционной");
            xsdMap.forEach((s, file) -> file.deleteOnExit());
            xmlMap.forEach((s, file) -> file.deleteOnExit());
            System.exit(0);
        }
        log.info("Обьектная схема валидна реляционной");
        xsdMap.forEach((s, file) -> file.deleteOnExit());
        xmlMap.forEach((s, file) -> file.deleteOnExit());
    }

    private String fromCamelToSnake(String camelCase) {

        StringBuilder result = new StringBuilder();

        char c = camelCase.charAt(0);
        result.append(Character.toLowerCase(c));

        for (int i = 1; i < camelCase.length(); i++) {

            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
