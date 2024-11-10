package hotel.system.grand.controller;

import hotel.system.grand.dto.*;
import hotel.system.grand.service.HallBookingService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hall-booking")
@RequiredArgsConstructor
@CrossOrigin
public class HallBookingController {
    final HallBookingService hallBookingService;

    @GetMapping("get-availableHall")
    public List<HallDTO> getAvailable(@PathParam("date") String date){
        return hallBookingService.findAvailableHall(date);
    }

    @PostMapping("/saveBooking")
    public HttpStatus saveRoomBooking(@RequestBody HallBookingDTO hallBookingDTO) {
        return hallBookingService.saveHallBooking(hallBookingDTO);
    }

    @GetMapping("/getAll")
    public List<HallBookingResponseDTO> getAllHallBookings(){
        return hallBookingService.getAllHallBookings();
    }

    @PostMapping("/cancelBooking/{id}")
    public HttpStatus cancelHallBooking(@PathVariable Integer id) {
        return hallBookingService.cancelBooking(id);
    }
}
