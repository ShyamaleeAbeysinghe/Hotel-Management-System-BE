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
@Table(name = "hall")
public class HallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private String hallName;
    private Double price;
    private Integer chairs;
    private Integer tables;
    private Integer status;
    @Column(columnDefinition = "LONGBLOB")
    private String img;

    @OneToMany(mappedBy = "hallEntity")
    private Set<HallBookingEntity> hallBookingEntities;

}
