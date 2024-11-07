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
    private String password;
    private Integer status;
    private Integer role;
    private String roleName;

    public StaffDTO(Integer id, String firstName, String lastName, String contact, String nic, String address, Integer role,String roleName) {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.contact=contact;
        this.nic=nic;
        this.address=address;
        this.role=role;
        this.roleName=roleName;
    }
}
