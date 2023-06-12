package validator;

import java.io.File;
import java.util.Map;

public interface Validator {

    void validate(Map<String, File> fileMap,Integer quantityTables) ;

    static String fromCamelToSnake(String camelCase) {
        StringBuilder result = new StringBuilder();

        char c = camelCase.charAt(0);
        result.append(Character.toLowerCase(c));

        for (int i = 1; i < camelCase.length(); i++) {

            char ch = camelCase.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append('_');
                result.append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
