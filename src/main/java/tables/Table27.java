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
@Table(tableName = "table27")
public class Table27 {

    @PrimaryKey
    Integer id;

    Timestamp startTime;

    Timestamp endTime;

    @ForeignKey
    Table24 table24Id;
}
