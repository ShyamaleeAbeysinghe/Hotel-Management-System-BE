package hotel.system.grand.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   private String customerName;
   private String age;
   private String address;
   private String contact;
   private String nic;
   private String email;
   private String userName;
   private String password;
   private Integer status;

    @OneToMany(mappedBy = "customerEntity")
    private Set<HallBookingEntity> hallBookingEntities;

}
