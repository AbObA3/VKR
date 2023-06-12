package tables;


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
@Table(tableName = "table12")
public class Table12 {

    @PrimaryKey
    Integer id;

    String address;

    String email;

}
