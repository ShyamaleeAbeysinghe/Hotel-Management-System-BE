package hotel.system.grand.controller;

import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
@CrossOrigin
public class HallController {
    final HallService hallService;

    @PostMapping("/add")
    public void addHall(@RequestBody HallDTO hallDTO){
        hallService.addHall(hallDTO);

    }
    @PutMapping("/update-hall")
    public void updateHall(@RequestBody HallDTO hallDTO){
        hallService.addHall(hallDTO);
    }

    @DeleteMapping("/delete-hall")
    public Boolean deleteById(Integer id){
        return hallService.deleteHall(id);
    }
    @GetMapping("/get-all")
    List<HallDTO> getAllHalls(){
        return hallService.getAll();
    }

    @GetMapping("/search-by-hallName/{hallName}")
    public List<HallDTO> searchHallDtoByHallName(@PathVariable String hallName){
        return hallService.searchHallDtoByHallName(hallName);
    }
}
