package hotel.system.grand.service;

import hotel.system.grand.dto.StaffDTO;

import java.util.List;

public interface StaffService {
    void addStaff(StaffDTO staffDTO);

    Boolean deleteById(Integer id);

    List<StaffDTO> getAll();
}
