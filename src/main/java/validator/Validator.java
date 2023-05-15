package validator;

import java.io.File;
import java.util.Map;

public interface Validator {

    void validate(Map<String, File> fileMap,Integer quantityTables) ;
}
