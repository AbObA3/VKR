package split;


import java.io.File;
import java.util.List;
import java.util.Map;

public interface Splitter {

    void genDocs(String xml);

    List<File> getFiles();

    Integer getTableQuantity();

    List<String> getRows(String xml);

    Map<String,String> getMapRows(String tag, String xml);

    String getData(String s, String tag);
}
