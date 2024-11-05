package hotel.system.grand.service;

import hotel.system.grand.dto.HallDTO;

import java.util.List;

public interface HallService {
    void addHall(HallDTO hallDTO);

    Boolean deleteHall(Integer id);

    List<HallDTO> getAll();

    List<HallDTO> searchHallDtoByHallName(String hallName);
}
