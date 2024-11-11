package hotel.system.grand.service;

import hotel.system.grand.dto.OrdersDTO;
import hotel.system.grand.dto.ResturentMenuDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface ResturentMenuService {
    HttpStatus addResturentMenu(ResturentMenuDTO resturentMenuDTO);

    Boolean deleteResturentMenu(Integer id);

    List<ResturentMenuDTO> getAll();

    List<ResturentMenuDTO> searchRoomDtoByFoodName(String foodName);

}
