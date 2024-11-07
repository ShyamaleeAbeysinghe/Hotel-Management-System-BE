package hotel.system.grand.controller;

import hotel.system.grand.dto.ResturentMenuDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.ResturentMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resturentMenu")
@RequiredArgsConstructor
@CrossOrigin

public class ResturentMenuController {
    final ResturentMenuService resturentMenuService;

    @PostMapping("/add-resturentMenu")
    public HttpStatus addResturentMenu(@RequestBody ResturentMenuDTO resturentMenuDTO){
        return resturentMenuService.addResturentMenu(resturentMenuDTO);
    }

    @PutMapping("/update-resturentMenu")
    public HttpStatus UpdateResturentMenu(@RequestBody ResturentMenuDTO resturentMenuDTO){
        return resturentMenuService.addResturentMenu(resturentMenuDTO);
    }

    @DeleteMapping("/delete-resturentMenu/{id}")
    public Boolean deleteById(@PathVariable  Integer id){
        return resturentMenuService.deleteResturentMenu(id);
    }
    @GetMapping("/get-all")
    List<ResturentMenuDTO> getAllResturentMenu()
    {
        return resturentMenuService.getAll();
    }

    @GetMapping("/search-by-foodName/{foodName}")
    public List<ResturentMenuDTO> searchRoomDtoByFoodName(@PathVariable String foodName){
        return resturentMenuService.searchRoomDtoByFoodName(foodName);
    }
}
