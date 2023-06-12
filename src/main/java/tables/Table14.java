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
@Table(tableName = "table14")
public class Table14 {

    @PrimaryKey
    Integer id;

    String description;

    String status;

    @ForeignKey
    Table15 table15Id;
}
