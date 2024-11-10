package hotel.system.grand.service;

import hotel.system.grand.dto.RoomDTO;

import java.util.List;

public interface RoomBookingService {
    List<RoomDTO> findAvailableRooms(String start,String end);
}
