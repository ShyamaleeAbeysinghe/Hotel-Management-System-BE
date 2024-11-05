package hotel.system.grand.service.impl;

import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.entity.RoomEntity;
import hotel.system.grand.repository.RoomRepository;
import hotel.system.grand.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    final RoomRepository roomRepository;
    final ModelMapper mapper;

    @Override
    public void addRoom(RoomDTO roomDTO) {
        roomRepository.save(mapper.map(roomDTO, RoomEntity.class));
    }

    @Override
    public Boolean deleteRoom(Integer id) {
         roomRepository.deleteById(id);
         return true;
    }

    @Override
    public List<RoomDTO> getAll() {
        List<RoomDTO> roomDTOList=new ArrayList<>();
        roomRepository.findAll().forEach(roomEntity -> {
            roomDTOList.add(mapper.map(roomEntity,RoomDTO.class));
        });
        return roomDTOList;
    }

    @Override
    public List<RoomDTO> searchRoomDtoByRoomName(String roomName) {
        List<RoomDTO> roomDTOList=new ArrayList<>();
        roomRepository.findByRoomName(roomName).forEach(roomEntity -> {
            roomDTOList.add(mapper.map(roomEntity,RoomDTO.class));
        });
        return roomDTOList;
    }

    @Override
    public List<RoomDTO> searchRoomDtoByRoomNo(Integer roomNo) {
        List<RoomDTO> roomDTOList=new ArrayList<>();
        roomRepository.findByRoomNo(roomNo).forEach(roomEntity -> {
            roomDTOList.add(mapper.map(roomEntity,RoomDTO.class));
        });
        return roomDTOList;
    }
}
