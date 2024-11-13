package hotel.system.grand.service.impl;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.dto.PasswordDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.HallEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository customerRepository;
    final ModelMapper mapper;
    @Override
    public HttpStatus addCustomer(CustomerDTO customerDTO) {
        try{
            CustomerEntity customerEntity = mapper.map(customerDTO,CustomerEntity.class);
            customerEntity.setStatus(1);
            customerRepository.save(customerEntity);
            return HttpStatus.CREATED;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

    @Override
    public HttpStatus updateCustomer(CustomerDTO customerDTO) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerDTO.getId());
        if (optionalCustomerEntity.isPresent()){
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            customerEntity.setCustomerName(customerDTO.getCustomerName());
            customerEntity.setAge(customerDTO.getAge());
            customerEntity.setAddress(customerDTO.getAddress());
            customerEntity.setContact(customerDTO.getContact());
            customerEntity.setEmail(customerDTO.getEmail());
            customerRepository.save(customerEntity);
            return HttpStatus.ACCEPTED;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public Map<String, String> updateCustomerPassword(PasswordDTO passwordDTO) {
        Map<String, String> response = new HashMap<>();
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(passwordDTO.getId());
        if (optionalCustomerEntity.isPresent()){
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            if (customerEntity.getPassword().equals(passwordDTO.getOldPassword())){
                customerEntity.setPassword(passwordDTO.getNewPassword());
                customerRepository.save(customerEntity);
                response.put("status","success");
            }else{
                response.put("status","failed");
                response.put("reason","Current password is invalid");
            }
            return response;
        }
        response.put("status","failed");
        response.put("reason","Incorrect user details. Please log in again");
        return response;
    }

    @Override
    public Boolean deleteCustomer(Integer id) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(id);
        if (optionalCustomerEntity.isPresent()){
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            customerEntity.setStatus(0);
            customerRepository.save(customerEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        customerRepository.findAll().forEach(customerEntity -> {
            if (customerEntity.getStatus()==1){
                CustomerDTO customerDTO = mapper.map(customerEntity, CustomerDTO.class);
                customerDTO.setUsername("");
                customerDTO.setPassword("");
                customerDTOList.add(customerDTO);
            }
        });
        return customerDTOList;
    }

    @Override
    public List<CustomerDTO> searchByCustomerName(String customerName) {
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        customerRepository.findByCustomerName(customerName).forEach(customerEntity -> {
            customerDTOList.add(mapper.map(customerEntity,CustomerDTO.class));
        });
        return customerDTOList;
    }

    @Override
    public List<CustomerDTO> searchByontact(String contact) {
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        customerRepository.findByContact(contact).forEach(customerEntity -> {
            customerDTOList.add(mapper.map(customerEntity,CustomerDTO.class));
        });
        return customerDTOList;
    }

    @Override
    public List<CustomerDTO> searchByEmail(String email) {
       List<CustomerDTO> customerDTOList=new ArrayList<>();
//       customerRepository.findByEmail(email).forEach(customerEntity -> {
//           customerDTOList.add(mapper.map(customerEntity,CustomerDTO.class));
//       });

       return customerDTOList;
    }

    @Override
    public CustomerDTO findCutomerById(Integer customerId) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerId);
        if (optionalCustomerEntity.isPresent()){
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            customerEntity.setUserName("");
            customerEntity.setPassword("");
            return mapper.map(customerEntity,CustomerDTO.class);
        }
        return null;
    }
}
