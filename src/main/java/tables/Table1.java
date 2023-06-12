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
@Table(tableName = "table1")
public class Table1 {

    @PrimaryKey
    Integer id;

    String name;

    Integer age;

    @ForeignKey
    Table4 table4Id;

}