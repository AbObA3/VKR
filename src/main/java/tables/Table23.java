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
@Table(tableName = "table23")
public class Table23 {

    @PrimaryKey
    Integer id;

    Integer rating;

    String review;

    @ForeignKey
    Table27 table27Id;

}
