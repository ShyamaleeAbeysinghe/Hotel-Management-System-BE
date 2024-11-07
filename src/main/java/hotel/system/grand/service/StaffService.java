package hotel.system.grand.service;

import hotel.system.grand.dto.StaffDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface StaffService {
    HttpStatus addStaff(StaffDTO staffDTO);

    Boolean deleteById(Integer id);

    List<StaffDTO> getAll();

    List<StaffDTO> searchStaffDtoByFirstName(String firstName);

    List<StaffDTO> searchStaffDtoByContact(String contact);

    List<StaffDTO> searchStaffDtoByNic(String nic);
}
