package hotel.system.grand.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resturentHasOrder")
public class ResturentManuHasOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer qty;

   @ManyToOne
    @JoinColumn(name="resturentManu_id",nullable = false)
    private ResturentMenuEntity resturentMenuEntity;

    @ManyToOne
    @JoinColumn(name="orders_id",nullable = false)
    private OrdersEntity ordersEntity;
}
