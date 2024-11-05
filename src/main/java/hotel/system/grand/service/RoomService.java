package hotel.system.grand.service;

import hotel.system.grand.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    void addRoom(RoomDTO roomDTO);

    Boolean deleteRoom(Integer id);

    List<RoomDTO> getAll();

    List<RoomDTO> searchRoomDtoByRoomName(String roomName);

    List<RoomDTO> searchRoomDtoByRoomNo(Integer roomNo);
}
