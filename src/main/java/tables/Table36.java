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
@Table(tableName = "table36")
public class Table36 {

    @PrimaryKey
    Integer id;

    String city;

    String country;

    @ForeignKey
    Table18 table18Id;
}
