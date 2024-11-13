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
   private Integer id;
   private String customerName;
   private String age;
   private String address;
   private String contact;
   private String email;
   private String username;
   private String password;
   private Integer status;

}
