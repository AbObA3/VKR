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
@Table(tableName = "table41")
public class Table41 {

    @PrimaryKey
    Integer id;

    Integer rating;

    String review;

    @ForeignKey
    Table40 table40Id;
}
