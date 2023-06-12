package tables;

import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(tableName = "table9")
public class Table9 {

    @PrimaryKey
    Integer id;

    Timestamp startTime;

    Timestamp endTime;

    @ForeignKey
    Table7 table7Id;
}
