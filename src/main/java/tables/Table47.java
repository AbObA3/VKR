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
@Table(tableName = "table47")
public class Table47 {

    @PrimaryKey
    Integer id;

    Integer quantity;

    Integer price;

    @ForeignKey
    Table50 table50Id;

}
