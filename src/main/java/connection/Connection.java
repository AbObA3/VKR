package connection;

import java.sql.SQLException;

public interface Connection {

    String sendQueryIntoDB(String schema) throws SQLException;

    String getQueryResult(String schemaName, String tableName);

    String getForeignQueryResult(String schemaName, String tableName, String idName, String idValue);
}
