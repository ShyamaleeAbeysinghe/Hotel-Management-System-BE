package hotel.system.grand.repository;

import hotel.system.grand.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    List<CustomerEntity> findByCustomerName(String customerName);

    List<CustomerEntity> findByContact(String contact);

    Optional<CustomerEntity> findByEmail(String email);
}
