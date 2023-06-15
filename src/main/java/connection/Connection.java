package connection;

import java.sql.SQLException;

public interface Connection {

    String sendQueryIntoDB(String schema) throws SQLException;

    String getQueryResult(String schemaName, String tableName);

}
