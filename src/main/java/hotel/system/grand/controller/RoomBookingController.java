package hotel.system.grand.controller;

import hotel.system.grand.dto.RoomBookingDTO;
import hotel.system.grand.dto.RoomBookingResponseDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.RoomBookingService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-booking")
@RequiredArgsConstructor
@CrossOrigin
public class RoomBookingController {
    final RoomBookingService roomBookingService;

    @GetMapping("get-availableRoom")
    public List<RoomDTO> getAvailableRoom(@PathParam("start") String start, @PathParam("end") String end) {
        return roomBookingService.findAvailableRooms(start, end);
    }

    @PostMapping("/saveBooking")
    public HttpStatus saveRoomBooking(@RequestBody RoomBookingDTO roomBookingDTO) {
        return roomBookingService.saveRoomBooking(roomBookingDTO);
    }

    @GetMapping("/getAll")
    public List<RoomBookingResponseDTO> getAllRoomBookings(){
        return roomBookingService.getAllRoomBookings();
    }


    @PostMapping("/cancelBooking/{id}")
    public HttpStatus cancelRoomBooking(@PathVariable Integer id) {
        return roomBookingService.cancelBooking(id);
    }
}
