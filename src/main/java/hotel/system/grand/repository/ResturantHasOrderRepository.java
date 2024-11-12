package hotel.system.grand.repository;

import hotel.system.grand.entity.OrdersEntity;
import hotel.system.grand.entity.ResturentManuHasOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResturantHasOrderRepository extends JpaRepository<ResturentManuHasOrderEntity,Integer> {
    List<ResturentManuHasOrderEntity> findAllByOrdersEntity(OrdersEntity ordersEntity);
}
