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
@Table(tableName = "table40")
public class Table40 {

    @PrimaryKey
    Integer id;

    Integer quantity;

    Integer price;

    @ForeignKey
    Table32 table32Id;
}
