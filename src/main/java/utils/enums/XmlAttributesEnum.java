package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public enum XmlAttributesEnum {

    RESTRICTION("restriction"),
    ELEMENT("element"),
    ROW("row"),
    SCHEMA("schema"),
    ROW_TYPE("rowType"),
    COMPLEX_TYPE("complexType"),
    SEQUENCE("sequence"),
    CONSTRAINT_TYPE("constraint_type"),
    SIMPLE_TYPE("simpleType"),
    BASE("base"),
    STRING("string"),
    ENUMERATION("enumeration"),
    VALUE("value"),
    COLUMN_NAME("column_name"),
    REFERENCE_TABLE("reference_table"),
    NILLABLE("nillable"),
    TRUE("true"),
    REFERENCE_COL("reference_col"),
    DATA_TYPE("data_type"),
    NUMERIC_PRECISION("numeric_precision"),
    TABLE_NAME("table_name"),
    TABLE_SCHEMA("table_schema");
    private String val;


    }
