package split.impl;

import connection.impl.ConnectionImpl;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import split.Splitter;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.logging.Logger;

@Data
public class SplitterImpl implements Splitter {

    private static final Logger log;
    private List<File> files;
    private Integer tableQuantity;

    static {
        log = Logger.getLogger(ConnectionImpl.class.getName());
    }

    public SplitterImpl() {
        this.tableQuantity = 0;
        this.files = new ArrayList<>();
    }

    public static int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }

    @Override
    public void genDocs(String xml) {
        Set<String> tableNames = new HashSet<>();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(xml.split("</row>")));
        list.remove(list.size() - 1);
        list.forEach(s -> {
            String tableName = s.substring(s.indexOf("<table_name>") + 12, s.indexOf("</table_name>"));
            tableNames.add(tableName);
            String name = tableName + "-" + s.substring(s.indexOf("<column_name>") + 13, s.indexOf("</column_name>"));
            File file = new File(String.format("src/main/resources/%s.xml", name));
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(s + "</row>");
                fileWriter.close();
                this.files.add(file);
            } catch (Exception e) {
                log.severe(e.getMessage());
            }
        });
        this.tableQuantity = tableNames.size();

    }
}
