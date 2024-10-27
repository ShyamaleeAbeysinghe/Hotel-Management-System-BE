package hotel.system.grand.service;

import hotel.system.grand.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    void addCustomer(CustomerDTO customerDTO);

    Boolean deleteCustomer(Integer id);

    List<CustomerDTO> getAll();
}
