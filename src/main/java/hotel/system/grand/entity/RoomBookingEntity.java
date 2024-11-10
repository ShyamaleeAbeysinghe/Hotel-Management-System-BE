package hotel.system.grand.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roomBooking")
public class RoomBookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private Integer status;
    private LocalDate checkIn;
    private LocalDate checkout;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private CustomerEntity customerEntity02;

    @ManyToOne
    @JoinColumn(name = "room_id",nullable = false)
    private RoomEntity roomEntity;

    @OneToMany(mappedBy = "roomBookingEntity")
    private Set<OrdersEntity> ordersEntities;
}
