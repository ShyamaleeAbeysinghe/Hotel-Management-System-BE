package hotel.system.grand.service.impl;

import hotel.system.grand.dto.LoginDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.StaffEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.repository.StaffRepository;
import hotel.system.grand.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final StaffRepository staffRepository;
    private final CustomerRepository customerRepository;
    @Override
    public Map<String,String> staffLogin(LoginDTO loginDTO) {
        Map<String,String> response = new HashMap<>();
        Optional<StaffEntity> optionalStaffEntity = staffRepository.findByNic(loginDTO.getUsername());
        if (optionalStaffEntity.isPresent()){
            StaffEntity staffEntity = optionalStaffEntity.get();
            if (staffEntity.getPassword().equals(loginDTO.getPassword())){
                response.put("status","success");
                response.put("role",staffEntity.getRoleEntity().getRoleName());
                return response;
            }else{
                response.put("status","failed");
                return response;
            }
        }else{
            response.put("status","failed");
            return response;
        }
    }

    @Override
    public Map<String, String> customerLogin(LoginDTO loginDTO) {
        Map<String,String> response = new HashMap<>();
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findByEmail(loginDTO.getUsername());
        if (optionalCustomerEntity.isPresent()){
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            if (customerEntity.getPassword().equals(loginDTO.getPassword())){
                response.put("status","success");
                response.put("user",customerEntity.getId().toString());
                return response;
            }else{
                response.put("status","failed");
                return response;
            }
        }
        response.put("status","failed");
        return response;
    }
}
