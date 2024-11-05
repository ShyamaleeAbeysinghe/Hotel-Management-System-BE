package hotel.system.grand.service.impl;

import hotel.system.grand.dto.StaffDTO;
import hotel.system.grand.entity.StaffEntity;
import hotel.system.grand.repository.StaffRepository;
import hotel.system.grand.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    final StaffRepository staffRepository;
    final ModelMapper mapper;

    @Override
    public void addStaff(StaffDTO staffDTO) {
      staffRepository.save(mapper.map(staffDTO, StaffEntity.class));
    }

    @Override
    public Boolean deleteById(Integer id) {
        staffRepository.deleteById(id);
        return true;
    }

    @Override
    public List<StaffDTO> getAll() {
        List<StaffDTO> staffDTOList=new ArrayList<>();
        staffRepository.findAll().forEach(staffEntity -> {
            staffDTOList.add(mapper.map(staffEntity,StaffDTO.class));
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
        List<StaffDTO> staffDTOList=new ArrayList<>();
        staffRepository.findByNic(nic).forEach(staffEntity -> {
            staffDTOList.add(mapper.map(staffEntity,StaffDTO.class));
        });
        return staffDTOList;
    }

}
