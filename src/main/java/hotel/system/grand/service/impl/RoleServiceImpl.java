package hotel.system.grand.service.impl;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.dto.RoleDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.RoleEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.repository.RoleRepository;
import hotel.system.grand.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    final RoleRepository roleRepository;
    final ModelMapper mapper;
    @Override
    public void addRole(RoleDTO roleDTO) {
        roleRepository.save(mapper.map(roleDTO, RoleEntity.class));
    }
    @Override
    public Boolean deleteRole(Integer id) {
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public List<RoleDTO> getAll() {
        List<RoleDTO> roleDTOList=new ArrayList<>();
        roleRepository.findAll().forEach(roleEntity -> {
            if(roleEntity.getStatus()==1){
                roleDTOList.add(mapper.map(roleEntity,RoleDTO.class));
            }

        });
        return roleDTOList;
    }

    @Override
    public List<RoleDTO> getRoleDtoByRoleName(String roleName) {
        List<RoleDTO> roleDTOList=new ArrayList<>();
        roleRepository.findByRoleName(roleName).forEach(roleEntity -> {
            roleDTOList.add(mapper.map(roleEntity,RoleDTO.class));
        });
        return roleDTOList;
    }


}
