package tables;

import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(tableName = "example2")
public class Example2 {

    @PrimaryKey
    private Integer example2Id;

    @ForeignKey
    private Example exampleId;

    private String requestMn;

}
