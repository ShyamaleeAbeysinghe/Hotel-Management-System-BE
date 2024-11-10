package hotel.system.grand.controller;

import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.service.HallBookingService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
