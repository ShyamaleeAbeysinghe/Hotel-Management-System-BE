package hotel.system.grand.service;

import hotel.system.grand.dto.HallBookingDTO;
import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.dto.RoomBookingDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface HallBookingService {
    List<HallDTO> findAvailableHall(String date);
    HttpStatus saveHallBooking(HallBookingDTO hallBookingDTO);
}
