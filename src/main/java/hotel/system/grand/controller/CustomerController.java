package hotel.system.grand.controller;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    final CustomerService customerService;
    @PostMapping("/add")
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
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
        return customerService.getAll();
    }

}
