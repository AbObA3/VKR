package org.example.schemagen;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "http://www.w3.org/2001/XMLSchema-instance", propOrder = {
        "constraintType",
        "tableSchema",
        "tableName",
        "columnName",
        "referenceTable",
        "referenceCol",
        "dataType",
        "numericPrecision"

})
@XmlRootElement(name = "row")
public class Row {
    public String getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(String constraintType) {
        this.constraintType = constraintType;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(String referenceTable) {
        this.referenceTable = referenceTable;
    }

    public String getReferenceCol() {
        return referenceCol;
    }

    public void setReferenceCol(String referenceCol) {
        this.referenceCol = referenceCol;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    @XmlElement(name = "constraint_type")
    private String constraintType;

    @XmlElement(name = "table_schema")
    private String tableSchema;

    @XmlElement(name = "table_name")
    private String tableName;

    @XmlElement(name = "column_name")
    private String columnName;

    @XmlElement(name = "reference_table")
    private String referenceTable;

    @XmlElement(name = "reference_col")
    private String referenceCol;


    @XmlElement(name = "data_type")
    private String dataType;

    @XmlElement(name = "numeric_precision")
    private Integer numericPrecision;


}

