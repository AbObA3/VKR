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
@Table(tableName = "table46")
public class Table46 {

    @PrimaryKey
    Integer id;

    String city;

    String country;

    @ForeignKey
    Table48 table48Id;
}
