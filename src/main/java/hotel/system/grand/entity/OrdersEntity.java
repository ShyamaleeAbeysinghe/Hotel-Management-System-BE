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
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalPrice;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "roomBooking_id",nullable = false)
    private RoomBookingEntity roomBookingEntity;

    @OneToMany(mappedBy = "ordersEntity")
    private Set<ResturentManuHasOrderEntity> resturentManuHasOrderEntities;
}
