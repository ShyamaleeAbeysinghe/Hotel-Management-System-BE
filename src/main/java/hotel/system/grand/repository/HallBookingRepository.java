package hotel.system.grand.repository;

import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.HallBookingEntity;
import hotel.system.grand.entity.RoomBookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HallBookingRepository extends JpaRepository<HallBookingEntity,Integer> {
    List<HallBookingEntity> findAllByBookedDate(LocalDate date);
    List<HallBookingEntity> findAllByBookedDateBetween(LocalDate start,LocalDate end);
    List<HallBookingEntity> findAllByCustomerEntity01(CustomerEntity customerEntity);

}
