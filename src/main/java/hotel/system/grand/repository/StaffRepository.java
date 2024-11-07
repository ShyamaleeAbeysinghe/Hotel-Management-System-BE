package hotel.system.grand.repository;

import hotel.system.grand.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<StaffEntity,Integer> {
    List<StaffEntity> findByFirstName(String firstName);

    List<StaffEntity> findByContact(String contact);

    Optional<StaffEntity> findByNic(String nic);
}
