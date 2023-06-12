package tables;

import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(tableName = "table15")
public class Table15 {

    @PrimaryKey
    Integer id;

    String title;

    Date releaseDate;

    @ForeignKey
    Table16 table16Id;

}
