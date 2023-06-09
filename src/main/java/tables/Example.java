package tables;


import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(tableName = "example",schemaName = "schema_5")
public class Example {

    @PrimaryKey
    private Integer id;

    private Long longValue;

    private String stringValue;

    private Float realValue;

    @ForeignKey
    private Example2 example2Id;



}
