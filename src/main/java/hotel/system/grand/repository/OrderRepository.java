package hotel.system.grand.repository;

import hotel.system.grand.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrdersEntity,Integer> {
}
