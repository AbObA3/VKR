package split;


import java.io.File;
import java.util.List;

public interface Splitter {

    void genDocs(String xml);

    List<File> getFiles();

    Integer getTableQuantity();

    List<String> getRows(String xml);
}
