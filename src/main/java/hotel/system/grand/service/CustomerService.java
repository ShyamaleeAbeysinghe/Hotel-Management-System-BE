package hotel.system.grand.service;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.dto.PasswordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface CustomerService {
    HttpStatus addCustomer(CustomerDTO customerDTO);
    HttpStatus updateCustomer(CustomerDTO customerDTO);
    Map<String,String> updateCustomerPassword(PasswordDTO passwordDTO);

    Boolean deleteCustomer(Integer id);

    List<CustomerDTO> getAll();

    List<CustomerDTO> searchByCustomerName(String customerName);

    List<CustomerDTO> searchByontact(String contact);

    List<CustomerDTO> searchByEmail(String email);

    CustomerDTO findCutomerById(Integer customerId);
}
