package hotel.system.grand.service;

import hotel.system.grand.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    HttpStatus addCustomer(CustomerDTO customerDTO);

    Boolean deleteCustomer(Integer id);

    List<CustomerDTO> getAll();

    List<CustomerDTO> searchByCustomerName(String customerName);

    List<CustomerDTO> searchByontact(String contact);

    List<CustomerDTO> searchByEmail(String email);
}
