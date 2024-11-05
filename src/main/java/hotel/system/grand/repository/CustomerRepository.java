package hotel.system.grand.repository;

import hotel.system.grand.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    List<CustomerEntity> findByCustomerName(String customerName);

    List<CustomerEntity> findByContact(String contact);

    List<CustomerEntity> findByEmail(String email);
}
