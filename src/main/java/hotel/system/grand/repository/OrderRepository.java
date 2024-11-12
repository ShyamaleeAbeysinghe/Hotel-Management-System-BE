package hotel.system.grand.repository;

import hotel.system.grand.entity.OrdersEntity;
import hotel.system.grand.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrdersEntity,Integer> {
    List<OrdersEntity> getAllOrdersByRoomBookingEntity(RoomBookingEntity roomBookingEntity);
}
