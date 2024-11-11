package hotel.system.grand.controller;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    final CustomerService customerService;

    @PostMapping("/add")
    public HttpStatus addCustomer(@RequestBody CustomerDTO customerDTO){
       return customerService.addCustomer(customerDTO);
    }
    @PutMapping("/update")
    public  HttpStatus updateCustomer(@RequestBody CustomerDTO customerDTO){

       return customerService.addCustomer(customerDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteByCustomerId(@PathVariable Integer id){
       return customerService.deleteCustomer(id);
    }

    @GetMapping("/get-all")
    public List<CustomerDTO> getAllCustomer(){
        return customerService.getAll();
    }
    @GetMapping("/search-by-customerName/{customerName}")
    public List<CustomerDTO> searchByCustomerName(@PathVariable String customerName){
        return customerService.searchByCustomerName(customerName);
    }
    @GetMapping("/search-by-contact/{contact}")
    public List<CustomerDTO> searchByontact(@PathVariable String contact){
        return customerService.searchByontact(contact);
    }
    @GetMapping("/search-by-email/{email}")
    public List<CustomerDTO> searchByEmail(@PathVariable String email){
        return customerService.searchByEmail(email);
    }
    @GetMapping("/search-by-id/{id}")
    public CustomerDTO findCutomerById(@PathVariable Integer id){
        return customerService.findCutomerById(id);
    }


}
