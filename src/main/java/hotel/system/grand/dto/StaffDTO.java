package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StaffDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String contact;
    private String nic;
    private String address;
    private String userName;
    private String password;
    private Integer status;
    private String roleName;
}
