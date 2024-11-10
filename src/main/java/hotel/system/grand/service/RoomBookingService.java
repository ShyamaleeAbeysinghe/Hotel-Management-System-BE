package hotel.system.grand.service;

import hotel.system.grand.dto.RoomBookingDTO;
import hotel.system.grand.dto.RoomBookingResponseDTO;
import hotel.system.grand.dto.RoomDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface RoomBookingService {
    List<RoomDTO> findAvailableRooms(String start,String end);
    HttpStatus saveRoomBooking(RoomBookingDTO roomBookingDTO);
    List<RoomBookingResponseDTO> getAllRoomBookings();
    HttpStatus cancelBooking(Integer bookingId);
}
