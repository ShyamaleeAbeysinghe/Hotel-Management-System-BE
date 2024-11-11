package hotel.system.grand.service;

import hotel.system.grand.dto.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public interface RoomBookingService {
    List<RoomDTO> findAvailableRooms(String start,String end);
    HttpStatus saveRoomBooking(RoomBookingDTO roomBookingDTO);
    List<RoomBookingResponseDTO> getUserRoomBookings(Integer userId);
    HttpStatus cancelBooking(Integer bookingId);
    List<ManageBookingDTO> getAllRoomBookings();
    Map<String,String> customerCheckIn(Integer bookingId);

    Boolean isCustomerCheckedIn(Integer customerId);
}
