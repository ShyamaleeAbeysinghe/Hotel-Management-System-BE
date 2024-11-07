package hotel.system.grand.entity;

import hotel.system.grand.config.EncryptionConfig;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String contact;
    @Column(unique=true ,nullable = false)
    private String nic;
    private String address;
    private String userName;
    @Convert(converter = EncryptionConfig.class)
    private String password;
    private Integer status;


    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private RoleEntity roleEntity;
}
