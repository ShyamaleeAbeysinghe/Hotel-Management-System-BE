package hotel.system.grand.controller;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    final CustomerService customerService;

    @PostMapping("/add")
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        System.out.println("aaaaa");
        customerService.addCustomer(customerDTO);
    }
    @PutMapping("/update")
    public  void updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
    }

    @DeleteMapping("/delete")
    public Boolean deleteByCustomerId(@RequestParam Integer id){
       return customerService.deleteCustomer(id);
    }

    @GetMapping("/get-all")
    public List<CustomerDTO> getAllCustomer(){
        System.out.println("okkkkkkkkkkkkkkkkkk");
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


}
