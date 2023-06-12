import operaion.OperationRepository;
import operaion.impl.OperationRepositoryImpl;
import tables.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Main.class.getSimpleName());
        try {
            long start = System.currentTimeMillis();
//            Executor executor = new ExecutorImpl();
//            executor.execute("schema_5");
            OperationRepository<Example> repository = new OperationRepositoryImpl<>(Example.class);
            List<Example> list = repository.selectAll();
            long finish = System.currentTimeMillis();
            list.forEach(s -> {
                System.out.println(s.getId());
                System.out.println(s.getStringValue());
                System.out.println(s.getLongValue());
                System.out.println(s.getRealValue());
                System.out.println(s.getExample2Id());
                System.out.println(s.getExample2Id().getDateValue());
                System.out.println(s.getExample2Id().getTimestampValue());
            });
            System.out.println("размер: " + list.size());
            System.out.println("Время выполнения: " + (float) (finish - start) / 1000 + " секунд");
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException exception) {
            log.severe(exception.getMessage());
        }


    }
}