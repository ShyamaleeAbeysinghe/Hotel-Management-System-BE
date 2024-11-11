package hotel.system.grand.service;

import hotel.system.grand.dto.*;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface HallBookingService {
    List<HallDTO> findAvailableHall(String date);
    HttpStatus saveHallBooking(HallBookingDTO hallBookingDTO);
    List<HallBookingResponseDTO> getAllHallBookings(Integer userId);
    HttpStatus cancelBooking(Integer bookingId);
}
