package hotel.system.grand.service;

import hotel.system.grand.dto.ResturentMenuDTO;

import java.util.List;

public interface ResturentMenuService {
    void addResturentMenu(ResturentMenuDTO resturentMenuDTO);

    Boolean deleteResturentMenu(Integer id);

    List<ResturentMenuDTO> getAll();

    List<ResturentMenuDTO> searchRoomDtoByFoodName(String foodName);
}
