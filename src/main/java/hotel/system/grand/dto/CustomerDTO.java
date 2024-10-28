package hotel.system.grand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
   private String customerName;
   private String age;
   private String address;
   private String contact;
   private String nic;
   private String email;
   private String userName;
   private String password;
   private Integer status;

}
