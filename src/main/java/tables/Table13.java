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
@Table(tableName = "table13")
public class Table13 {

    @PrimaryKey
    Integer id;

    Integer salary;

    Date hireDate;

    @ForeignKey
    Table11 table11Id;
}
