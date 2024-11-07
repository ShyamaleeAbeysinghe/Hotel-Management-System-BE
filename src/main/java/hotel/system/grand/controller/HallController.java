package hotel.system.grand.controller;

import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.HallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
@CrossOrigin
public class HallController {
    final HallService hallService;

    @PostMapping("/add")
    public HttpStatus addHall(@RequestBody HallDTO hallDTO){
        return hallService.addHall(hallDTO);

    }
    @PutMapping("/update-hall")
    public HttpStatus updateHall(@RequestBody HallDTO hallDTO){

        return hallService.addHall(hallDTO);
    }

    @DeleteMapping("/delete-hall/{id}")
    public Boolean deleteById(@PathVariable Integer id){
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
