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
@Table(tableName = "table31")
public class Table31 {

    @PrimaryKey
    Integer id;

    String name;

    Integer age;

    @ForeignKey
    Table30 table30Id;

}
