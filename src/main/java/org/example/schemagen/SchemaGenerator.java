package org.example.schemagen;

import connection.impl.ConnectionImpl;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utils.enums.XmlAttributesEnum;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@NoArgsConstructor
public class SchemaGenerator {

    private static final Logger log;

    static {
        log = Logger.getLogger(ConnectionImpl.class.getName());
    }

    private static final String NS_PREFIX = "xs:";

    public Map.Entry<String, File> genSchemaXML(Row pRow) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            Element schemaRoot = doc.createElement(XmlAttributesEnum.ROW.getVal());
            schemaRoot.setAttribute("xmlns:xsi",XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
            doc.appendChild(schemaRoot);

            Element constraintType = doc.createElement(XmlAttributesEnum.CONSTRAINT_TYPE.getVal());
            constraintType.setTextContent(pRow.getConstraintType());
            schemaRoot.appendChild(constraintType);

            Element tableSchema = doc.createElement(XmlAttributesEnum.TABLE_SCHEMA.getVal());
            tableSchema.setTextContent(pRow.getTableSchema());
            schemaRoot.appendChild(tableSchema);

            Element tableName = doc.createElement(XmlAttributesEnum.TABLE_NAME.getVal());
            tableName.setTextContent(pRow.getTableName());
            schemaRoot.appendChild(tableName);

            Element columnName = doc.createElement(XmlAttributesEnum.COLUMN_NAME.getVal());
            columnName.setTextContent(pRow.getColumnName());
            schemaRoot.appendChild(columnName);

            Element referenceTable = doc.createElement(XmlAttributesEnum.REFERENCE_TABLE.getVal());
            referenceTable.setTextContent(pRow.getReferenceTable());
            schemaRoot.appendChild(referenceTable);

            Element referenceCol = doc.createElement(XmlAttributesEnum.REFERENCE_COL.getVal());
            referenceCol.setTextContent(pRow.getReferenceCol());
            schemaRoot.appendChild(referenceCol);

            Element dataType = doc.createElement(XmlAttributesEnum.DATA_TYPE.getVal());
            dataType.setTextContent(pRow.getDataType());
            schemaRoot.appendChild(dataType);

            Element numericPrecision = doc.createElement(XmlAttributesEnum.NUMERIC_PRECISION.getVal());
            numericPrecision.setTextContent(String.valueOf(pRow.getNumericPrecision()));
            schemaRoot.appendChild(numericPrecision);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);

            File file = new File(String.format("src/main/resources/%s.xml", pRow.getTableName() + "-" + pRow.getColumnName()));
            transformer.transform(domSource, new StreamResult(file));
            log.log(Level.INFO,"{0} schema generated", pRow.getTableName() + "-" + pRow.getColumnName());
            return Map.entry(pRow.getTableName() + "-" + pRow.getColumnName(),file);
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return null;
    }
    public File genSchemaXSD(Row pRow) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            Element schemaRoot = doc.createElementNS(XMLConstants.W3C_XML_SCHEMA_NS_URI, NS_PREFIX + XmlAttributesEnum.SCHEMA.getVal());
            doc.appendChild(schemaRoot);
            NameTypeElementMaker elMaker = new NameTypeElementMaker(NS_PREFIX, doc);

            Element row = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.ROW.getVal(), XmlAttributesEnum.ROW_TYPE.getVal());
            schemaRoot.appendChild(row);
            Element rowType = elMaker.createElement(XmlAttributesEnum.COMPLEX_TYPE.getVal(), XmlAttributesEnum.ROW_TYPE.getVal());
            schemaRoot.appendChild(rowType);
            Element sequence = elMaker.createElement(XmlAttributesEnum.SEQUENCE.getVal());
            rowType.appendChild(sequence);

            Element constraintType = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.CONSTRAINT_TYPE.getVal());
            sequence.appendChild(constraintType);
            Element constraintTypeSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            constraintType.appendChild(constraintTypeSimple);
            Element constraintTypeRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            constraintTypeRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            constraintTypeSimple.appendChild(constraintTypeRestriction);
            Element constraintTypeEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            constraintTypeEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getConstraintType());
            constraintTypeRestriction.appendChild(constraintTypeEnum);

            Element tableSchema = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.TABLE_SCHEMA.getVal());
            sequence.appendChild(tableSchema);
            Element tableSchemaSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            tableSchema.appendChild(tableSchemaSimple);
            Element tableSchemaRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            tableSchemaRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            tableSchemaSimple.appendChild(tableSchemaRestriction);
            Element tableSchemaEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            tableSchemaEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getTableSchema());
            tableSchemaRestriction.appendChild(tableSchemaEnum);

            Element tableName = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.TABLE_NAME.getVal());
            sequence.appendChild(tableName);
            Element tableNameSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            tableName.appendChild(tableNameSimple);
            Element tableNameRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            tableNameRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            tableNameSimple.appendChild(tableNameRestriction);
            Element tableNameEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            tableNameEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getTableName());
            tableNameRestriction.appendChild(tableNameEnum);

            Element columnName = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.COLUMN_NAME.getVal());
            sequence.appendChild(columnName);
            Element columnNameSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            columnName.appendChild(columnNameSimple);
            Element columnNameRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            columnNameRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            columnNameSimple.appendChild(columnNameRestriction);
            Element columnNameEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            columnNameEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getColumnName());
            columnNameRestriction.appendChild(columnNameEnum);

            Element referenceTable = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.REFERENCE_TABLE.getVal());
            referenceTable.setAttribute(XmlAttributesEnum.NILLABLE.getVal(), XmlAttributesEnum.TRUE.getVal());
            sequence.appendChild(referenceTable);
            Element referenceTableSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            referenceTable.appendChild(referenceTableSimple);
            Element referenceTableRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            referenceTableRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            referenceTableSimple.appendChild(referenceTableRestriction);
            Element referenceTableEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            referenceTableEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getReferenceTable());
            referenceTableRestriction.appendChild(referenceTableEnum);

            Element referenceColumn = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.REFERENCE_COL.getVal());
            referenceColumn.setAttribute(XmlAttributesEnum.NILLABLE.getVal(), XmlAttributesEnum.TRUE.getVal());
            sequence.appendChild(referenceColumn);
            Element referenceColumnSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            referenceColumn.appendChild(referenceColumnSimple);
            Element referenceColumnRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            referenceColumnRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            referenceColumnSimple.appendChild(referenceColumnRestriction);
            Element referenceColumnEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            referenceColumnEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getReferenceCol());
            referenceColumnRestriction.appendChild(referenceColumnEnum);

            Element dataType = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.DATA_TYPE.getVal());
            sequence.appendChild(dataType);
            Element dataTypeSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            dataType.appendChild(dataTypeSimple);
            Element dataTypeRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            dataTypeRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            dataTypeSimple.appendChild(dataTypeRestriction);
            Element dataTypeEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            dataTypeEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), pRow.getDataType());
            dataTypeRestriction.appendChild(dataTypeEnum);


            Element numericPrecision = elMaker.createElement(XmlAttributesEnum.ELEMENT.getVal(), XmlAttributesEnum.NUMERIC_PRECISION.getVal());
            sequence.appendChild(numericPrecision);
            Element numericPrecisionSimple = elMaker.createElement(XmlAttributesEnum.SIMPLE_TYPE.getVal());
            numericPrecision.appendChild(numericPrecisionSimple);
            Element numericPrecisionRestriction = elMaker.createElement(XmlAttributesEnum.RESTRICTION.getVal());
            numericPrecisionRestriction.setAttribute(XmlAttributesEnum.BASE.getVal(), NS_PREFIX + XmlAttributesEnum.STRING.getVal());
            numericPrecisionSimple.appendChild(numericPrecisionRestriction);
            Element numericPrecisionEnum = elMaker.createElement(XmlAttributesEnum.ENUMERATION.getVal());
            numericPrecisionEnum.setAttribute(XmlAttributesEnum.VALUE.getVal(), String.valueOf(pRow.getNumericPrecision()));
            numericPrecisionRestriction.appendChild(numericPrecisionEnum);


            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);

            File file = new File(String.format("src/main/resources/%s.xsd", pRow.getTableName() + "-" + pRow.getColumnName()));
            transformer.transform(domSource, new StreamResult(file));
            log.log(Level.INFO,"{0} schema generated", pRow.getTableName() + "-" + pRow.getColumnName());
            return file;
        } catch (Exception e) {

            log.severe(e.getMessage());
        }
        return null;
    }


    private static class NameTypeElementMaker {
        private final String nsPrefix;
        private final Document doc;

        public NameTypeElementMaker(String nsPrefix, Document doc) {
            this.nsPrefix = nsPrefix;
            this.doc = doc;
        }

        public Element createElement(String elementName, String nameAttrVal, String typeAttrVal) {
            Element element = doc.createElementNS(XMLConstants.W3C_XML_SCHEMA_NS_URI, nsPrefix + elementName);
            if (nameAttrVal != null)
                element.setAttribute("name", nameAttrVal);
            if (typeAttrVal != null)
                element.setAttribute("type", typeAttrVal);
            return element;
        }

        public Element createElement(String elementName, String nameAttrVal) {
            return createElement(elementName, nameAttrVal, null);
        }

        public Element createElement(String elementName) {
            return createElement(elementName, null, null);
        }
    }
}