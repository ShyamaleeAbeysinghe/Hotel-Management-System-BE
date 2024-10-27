package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Integer id;
    private String roleName;
    private Integer status;
}
