package hotel.system.grand.service.impl;

import hotel.system.grand.dto.StaffDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.HallEntity;
import hotel.system.grand.entity.RoleEntity;
import hotel.system.grand.entity.StaffEntity;
import hotel.system.grand.repository.RoleRepository;
import hotel.system.grand.repository.StaffRepository;
import hotel.system.grand.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    final StaffRepository staffRepository;
    final RoleRepository roleRepository;
    final ModelMapper mapper;

    @Override
    public HttpStatus addStaff(StaffDTO staffDTO) {
        try{
            Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(staffDTO.getRole());
            if (optionalRoleEntity.isPresent()){
                RoleEntity roleEntity = optionalRoleEntity.get();

                StaffEntity staffEntity = mapper.map(staffDTO, StaffEntity.class);
                staffEntity.setStatus(1);
                staffEntity.setPassword("Grand@staff1");
                staffEntity.setRoleEntity(roleEntity);
                staffRepository.save(staffEntity);
                return HttpStatus.CREATED;
            }else{
                return HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
        Optional<StaffEntity> optionalStaffEntity = staffRepository.findById(id);
        if (optionalStaffEntity.isPresent()){
            StaffEntity staffEntity = optionalStaffEntity.get();
            staffEntity.setStatus(0);
            staffRepository.save(staffEntity);
            return true;
        }
        return false;
    }

    @Override
    public List<StaffDTO> getAll() {
        List<StaffDTO> staffDTOList=new ArrayList<>();
        staffRepository.findAll().forEach(staffEntity -> {
            if(staffEntity.getStatus()==1){
                StaffDTO staffDTO=new StaffDTO(
                        staffEntity.getId(),
                        staffEntity.getFirstName(),
                        staffEntity.getLastName(),
                        staffEntity.getContact(),
                        staffEntity.getNic(),
                        staffEntity.getAddress(),
                        staffEntity.getRoleEntity().getId(),
                        staffEntity.getRoleEntity().getRoleName());
                staffDTOList.add(staffDTO);
            }

        });
        return staffDTOList;
    }

    @Override
    public List<StaffDTO> searchStaffDtoByFirstName(String firstName) {
        List<StaffDTO> staffDTOList=new ArrayList<>();
        staffRepository.findByFirstName(firstName).forEach(staffEntity -> {
            staffDTOList.add(mapper.map(staffEntity,StaffDTO.class));
        });
        return staffDTOList;
    }

    @Override
    public List<StaffDTO> searchStaffDtoByContact(String contact) {
        List<StaffDTO> staffDTOList=new ArrayList<>();
        staffRepository.findByContact(contact).forEach(staffEntity -> {
            staffDTOList.add(mapper.map(staffEntity,StaffDTO.class));
        });
        return staffDTOList;
    }

    @Override
    public List<StaffDTO> searchStaffDtoByNic(String nic) {
//        List<StaffDTO> staffDTOList=new ArrayList<>();
//        staffRepository.findByNic(nic).forEach(staffEntity -> {
//            staffDTOList.add(mapper.map(staffEntity,StaffDTO.class));
//        });
//        return staffDTOList;
        return null;
    }

}
