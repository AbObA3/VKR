package tables;

import annotations.PrimaryKey;
import annotations.Table;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(tableName = "example3")
public class Example3 {

    @PrimaryKey
    private Integer example3Id;

    private Timestamp grantDt;

    private String exName;

    private Integer age;
}
