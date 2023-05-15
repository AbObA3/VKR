package execution.impl;

import connection.Connection;
import connection.impl.ConnectionImpl;
import execution.Executor;
import org.example.schemagen.Row;
import org.example.schemagen.SchemaGenerator;
import split.Splitter;
import split.impl.SplitterImpl;
import validator.Validator;
import validator.impl.ValidatorImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ExecutorImpl implements Executor {
    /**
     * @param schemaName
     */
    @Override
    public void execute(String schemaName) throws SQLException, JAXBException {

        Connection connection = new ConnectionImpl();

        Splitter splitter = new SplitterImpl();
        splitter.genDocs(connection.sendQueryIntoDB(schemaName));
        JAXBContext jaxbContext = JAXBContext.newInstance(Row.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SchemaGenerator generator = new SchemaGenerator();
        Map<String, File> fileMap = new HashMap<>();
        splitter.getFiles().forEach(s -> {
            try {
                Row row = (Row) jaxbUnmarshaller.unmarshal(s);
                fileMap.put(row.getTableName() + "-" + row.getColumnName(), generator.genSchemaXSD(row));
                s.deleteOnExit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Validator validator = new ValidatorImpl();
        validator.validate(fileMap, splitter.getTableQuantity());

    }
}
