package hotel.system.grand.controller;

import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    final RoomService roomService;

    @PostMapping("/add-room")
    public HttpStatus addRoom(@RequestBody RoomDTO roomDTO){
        return roomService.addRoom(roomDTO);
    }

    @PutMapping("/update-room")
    public HttpStatus UpdateRoom(@RequestBody RoomDTO roomDTO){
        return roomService.addRoom(roomDTO);
    }

    @DeleteMapping("/delete-room/{id}")
    public Boolean deleteRoom(@PathVariable Integer id){
        return roomService.deleteRoom(id);
    }
    @GetMapping("/get-all")
    List<RoomDTO> getAllRooms(){
        return roomService.getAll();
    }

    @GetMapping("/search-by-roomName/{roomName}")
    public List<RoomDTO> searchRoomDtoByRoomName(@PathVariable String roomName){
        return roomService.searchRoomDtoByRoomName(roomName);
    }
    @GetMapping("/search-by-roomNo/{roomNo}")
    public List<RoomDTO> searchRoomDtoByRoomNo(@PathVariable Integer roomNo){
        return roomService.searchRoomDtoByRoomNo(roomNo);
    }
}
