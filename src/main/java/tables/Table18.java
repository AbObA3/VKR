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
@Table(tableName = "table18")
public class Table18 {

    @PrimaryKey
    Integer id;

    Integer rating;

    String review;

    @ForeignKey
    Table19 table19Id;
}
