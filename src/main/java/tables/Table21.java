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
@Table(tableName = "table21")
public class Table21 {

    @PrimaryKey
    Integer id;

    Integer quantity;

    Integer price;

    @ForeignKey
    Table20 table20Id;
}
