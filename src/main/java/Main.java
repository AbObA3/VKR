import execution.Executor;
import execution.impl.ExecutorImpl;
import operaion.OperationRepository;
import operaion.impl.OperationRepositoryImpl;
import tables.Example;

import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Main.class.getSimpleName());
        try {
            float time = 0f;
            for (int i = 0; i< 10; i++) {
                long start = System.currentTimeMillis();
                OperationRepository<Example> repository = new OperationRepositoryImpl<>(Example.class);
                List<Example> list = repository.selectAll();
                long finish = System.currentTimeMillis();
                time += (float) (finish - start) / 1000;
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
            }




            System.out.println("Время выполнения: " + time / 10 + " секунд");
        } catch (InvocationTargetException | InstantiationException |
                 IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }
}