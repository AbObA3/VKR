package connection.impl;

import connection.Connection;
import lombok.Data;
import org.example.configuration.GetProps;
import org.example.configuration.GetPropsImpl;
import org.example.configuration.PropertyConfig;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;


@Data
public class ConnectionImpl implements Connection {

    private static final Logger log;
    private java.sql.Connection connection;

    private GetProps getProps;
    private Statement statement;

    private String query = "select query_to_xml('with constraints as (select distinct pgc.contype                                                        as constraint_type,\n" +
            "                                     ccu.table_schema                                                   as table_schema,\n" +
            "                                     kcu.table_name                                                     as table_name,\n" +
            "                                     case\n" +
            "                                         when (pgc.contype = ''f'') then kcu.column_name\n" +
            "                                         else ccu.column_name end                                       as column_name,\n" +
            "                                     case when (pgc.contype = ''f'') then ccu.table_name else (null) end  as reference_table,\n" +
            "                                     case when (pgc.contype = ''f'') then ccu.column_name else (null) end as reference_col,\n" +
            "                                     d.data_type                                                        as data_type,\n" +
            "                                     case when d.numeric_precision is null then 0 else d.numeric_precision end as numeric_precision\n" +
            "                     from pg_constraint as pgc\n" +
            "                              join pg_namespace nsp on nsp.oid = pgc.connamespace\n" +
            "                              join pg_class cls on pgc.conrelid = cls.oid\n" +
            "                              join information_schema.key_column_usage kcu on kcu.constraint_name = pgc.conname\n" +
            "                              left join information_schema.constraint_column_usage ccu\n" +
            "                                        on pgc.conname = ccu.constraint_name\n" +
            "                                            and nsp.nspname = ccu.constraint_schema\n" +
            "                              join information_schema.columns d\n" +
            "                                   on ccu.table_schema = d.table_schema and ccu.column_name = d.column_name)\n" +
            "select t.*\n" +
            "from (select *\n" +
            "      from constraints\n" +
            "      union\n" +
            "      select ''c''  as constraint_type,\n" +
            "             table_schema,\n" +
            "             table_name,\n" +
            "             column_name,\n" +
            "             null as referenced_table,\n" +
            "             null as reference_col,\n" +
            "             data_type,\n" +
            "             case when numeric_precision is null then 0 else numeric_precision end as numeric_precision\n" +
            "      from information_schema.columns s\n" +
            "      where not exists(select distinct table_name, column_name\n" +
            "                       from constraints c\n" +
            "                       where c.table_name = s.table_name\n" +
            "                         and c.column_name = s.column_name)) t\n" +
            "where table_schema = ''%s''\n" +
            "order by table_name, constraint_type desc;', true\n" +
            "           , true, '');";

    static {
        log = Logger.getLogger(ConnectionImpl.class.getName());
    }

    public ConnectionImpl() {
        try {
            getProps = new GetPropsImpl();
            PropertyConfig propertyConfig = getProps.getConn();
            this.connection = DriverManager.getConnection(propertyConfig.getUrl(), propertyConfig.getUser(), propertyConfig.getPassword());
            this.connection.setAutoCommit(false);
            this.statement = connection.createStatement();
            log.info("connection success");
        } catch (Exception ex) {
            log.severe(ex.getMessage());
        }
    }

    @Override
    public String sendQueryIntoDB(String schema) {
        String result = null;
        try (PreparedStatement pstmt = this.connection.prepareStatement(String.format(query,schema))) {
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("query_to_xml");
            }
        } catch (Exception e) {
            log.severe(e.getMessage());
        }
        return result;
    }
}
