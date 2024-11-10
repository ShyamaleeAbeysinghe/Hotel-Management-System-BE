package hotel.system.grand.service;

import hotel.system.grand.dto.HallDTO;

import java.util.List;

public interface HallBookingService {
    List<HallDTO> findAvailableHall(String date);
}
