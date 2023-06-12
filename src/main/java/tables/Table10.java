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
@Table(tableName = "table10")
public class Table10 {

    @PrimaryKey
    Integer id;

    String category;

    Integer quantity;

    @ForeignKey
    Table12 table12Id;

}
