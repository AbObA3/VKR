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
@Table(tableName = "table48")
public class Table48 {

    @PrimaryKey
    Integer id;

    String title;

    Date releaseDate;

    @ForeignKey
    Table49 table49Id;
}
