package hotel.system.grand.service;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.dto.RoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    void addRole(RoleDTO customerDTO);

    Boolean deleteRole(Integer id);

    List<RoleDTO> getAll();
}
