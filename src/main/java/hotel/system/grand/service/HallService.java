package hotel.system.grand.service;

import hotel.system.grand.dto.HallDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface HallService {
    HttpStatus addHall(HallDTO hallDTO);

    Boolean deleteHall(Integer id);

    List<HallDTO> getAll();

    List<HallDTO> searchHallDtoByHallName(String hallName);
}
