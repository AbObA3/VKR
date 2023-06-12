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
@Table(tableName = "table5")
public class Table5 {

    @PrimaryKey
    Integer id;

    String title;

    Date releaseDate;

    @ForeignKey
    Table6 table6Id;
}
