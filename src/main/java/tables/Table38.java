package tables;

import annotations.ForeignKey;
import annotations.PrimaryKey;
import annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(tableName = "table38")
public class Table38 {

    @PrimaryKey
    Integer id;

    Timestamp startTime;

    Timestamp endTime;

    @ForeignKey
    Table34 table34Id;
}
