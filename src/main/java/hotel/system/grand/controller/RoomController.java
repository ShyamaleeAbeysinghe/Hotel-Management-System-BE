package hotel.system.grand.controller;

import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    final RoomService roomService;

    @PostMapping("/add-room")
    public void addRoom(@RequestBody RoomDTO roomDTO){
        roomService.addRoom(roomDTO);
    }

    @PutMapping("/update-room")
    public void UpdateRoom(@RequestBody RoomDTO roomDTO){
        roomService.addRoom(roomDTO);
    }

    @DeleteMapping("/delete-room")
    public Boolean deleteById(Integer id){
        return roomService.deleteRoom(id);
    }

    List<RoomDTO> getAllRooms(){
        return roomService.getAll();
    }
}
