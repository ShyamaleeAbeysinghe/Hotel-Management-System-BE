package hotel.system.grand.controller;

import hotel.system.grand.dto.StaffDTO;
import hotel.system.grand.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@CrossOrigin
public class StaffController {
    final StaffService staffService;

    @PostMapping("/add-staff")
    public void addStaff(@RequestBody StaffDTO staffDTO){
        staffService.addStaff(staffDTO);
    }
    @PutMapping("/update-staff")
    public void updateStaff(@RequestBody StaffDTO staffDTO){
        staffService.addStaff(staffDTO);
    }
    @DeleteMapping("/delete-staff")
    public Boolean deleteById(@RequestParam Integer id){
        return staffService.deleteById(id);
    }
    @GetMapping("/get-all")
    List<StaffDTO> getAll(){
        return staffService.getAll();
    }
}
