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
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition="LONGBLOB")
    private String img;
    private String roomName;
    private Integer roomNo;
    private Double price;
    private String size;
    private Integer beds;
    private String description;
    private Integer status;

    @OneToMany(mappedBy = "roomEntity")
    private Set<RoomBookingEntity> roomBookingEntities;
}
