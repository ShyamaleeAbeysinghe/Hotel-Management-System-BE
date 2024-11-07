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
@Table(name = "resturentManu")
public class ResturentMenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String foodName;
    private Double price;
    private String description;
    private Integer status;
    @Column(columnDefinition = "LONGBLOB")
    private String img;

    @OneToMany(mappedBy = "resturentMenuEntity")
    private Set<ResturentManuHasOrderEntity> resturentManuHasOrderEntities;
}
