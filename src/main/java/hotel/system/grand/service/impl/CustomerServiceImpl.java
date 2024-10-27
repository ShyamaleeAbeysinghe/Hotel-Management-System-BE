package hotel.system.grand.service.impl;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository customerRepository;
    final ModelMapper mapper;
    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        customerRepository.save(mapper.map(customerDTO,CustomerEntity.class));
    }
    @Override
    public Boolean deleteCustomer(Integer id) {
         customerRepository.deleteById(id);
         return true;
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customerDTOList=new ArrayList<>();
        customerRepository.findAll().forEach(customerEntity -> {
            customerDTOList.add(mapper.map(customerEntity,CustomerDTO.class));
        });
        return customerDTOList;
    }
}
