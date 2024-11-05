package hotel.system.grand.controller;

import hotel.system.grand.dto.ResturentMenuDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.ResturentMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resturentMenu")
@RequiredArgsConstructor
@CrossOrigin

public class ResturentMenuController {
    final ResturentMenuService resturentMenuService;

    @PostMapping("/add-resturentMenu")
    public void addResturentMenu(@RequestBody ResturentMenuDTO resturentMenuDTO){
        resturentMenuService.addResturentMenu(resturentMenuDTO);
    }

    @PutMapping("/update-resturentMenu")
    public void UpdateResturentMenu(@RequestBody ResturentMenuDTO resturentMenuDTO){
        resturentMenuService.addResturentMenu(resturentMenuDTO);
    }

    @DeleteMapping("/delete-resturentMenu")
    public Boolean deleteById(Integer id){
        return resturentMenuService.deleteResturentMenu(id);
    }
    @GetMapping("/get-all")
    List<ResturentMenuDTO> getAllResturentMenu(){
        return resturentMenuService.getAll();
    }

    @GetMapping("/search-by-foodName/{foodName}")
    public List<ResturentMenuDTO> searchRoomDtoByFoodName(@PathVariable String foodName){
        return resturentMenuService.searchRoomDtoByFoodName(foodName);
    }
}
