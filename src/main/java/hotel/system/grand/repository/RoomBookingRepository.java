package hotel.system.grand.repository;

import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.OrdersEntity;
import hotel.system.grand.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RoomBookingRepository extends JpaRepository<RoomBookingEntity,Integer> {
    List<RoomBookingEntity> findAllByCheckInBetween(LocalDate from,LocalDate to);
    List<RoomBookingEntity> findAllByCheckoutBetween(LocalDate from,LocalDate to);
    List<RoomBookingEntity> findAllByCustomerEntity02(CustomerEntity customerEntity);
}
