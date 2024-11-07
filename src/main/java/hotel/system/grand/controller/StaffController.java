package hotel.system.grand.controller;

import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.dto.StaffDTO;
import hotel.system.grand.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@CrossOrigin
public class StaffController {
    final StaffService staffService;

    @PostMapping("/add-staff")
    public HttpStatus addStaff(@RequestBody StaffDTO staffDTO){
        return staffService.addStaff(staffDTO);
    }
    @PutMapping("/update-staff")
    public HttpStatus updateStaff(@RequestBody StaffDTO staffDTO){
        return staffService.addStaff(staffDTO);
    }
    @DeleteMapping("/delete-staff/{id}")
    public Boolean deleteById(@PathVariable Integer id){
        return staffService.deleteById(id);
    }
    @GetMapping("/get-all")
    List<StaffDTO> getAll(){
        return staffService.getAll();
    }
    @GetMapping("/search-by-firstName/{firstName}")
    public List<StaffDTO> searchStaffDtoByFirstName(@PathVariable String firstName){
        return staffService.searchStaffDtoByFirstName(firstName);
    }
    @GetMapping("/search-by-contact/{contact}")
    public List<StaffDTO> searchStaffDtoByContact(@PathVariable String contact){
        return staffService.searchStaffDtoByContact(contact);
    }
    @GetMapping("/search-by-nic/{nic}")
    public List<StaffDTO> searchStaffDtoByNic(@PathVariable String nic){
        return staffService.searchStaffDtoByNic(nic);
    }
}
