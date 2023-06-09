package tables;


import annotations.PrimaryKey;
import annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(tableName = "example2",schemaName = "schema_5")
public class Example2 {

    @PrimaryKey
    private Integer id;

    private Double doubleValue;

    private Date dateValue;

    private Timestamp timestampValue;

    private Boolean boolValue;
}
