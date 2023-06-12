package operaion;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface OperationRepository<T> {

    List<T> selectAll() throws InvocationTargetException, InstantiationException, IllegalAccessException;

}
