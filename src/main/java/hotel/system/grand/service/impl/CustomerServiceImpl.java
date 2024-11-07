package hotel.system.grand.service.impl;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.HallEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                customerDTOList.add(mapper.map(customerEntity,CustomerDTO.class));
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
       customerRepository.findByEmail(email).forEach(customerEntity -> {
           customerDTOList.add(mapper.map(customerEntity,CustomerDTO.class));
       });

       return customerDTOList;
    }
}
