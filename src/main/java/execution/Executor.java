package execution;

import javax.xml.bind.JAXBException;
import java.sql.SQLException;

public interface Executor {

    void execute(String schemaName) throws SQLException, JAXBException;
}
