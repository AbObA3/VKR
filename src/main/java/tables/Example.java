package tables;

import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(tableName = "example")
public class Example {

    @PrimaryKey
    private Integer exampleId;

    private Timestamp grantDt;

    private String exName;

    private Integer age;

    @ForeignKey
    private Example3 example3Id;


}
