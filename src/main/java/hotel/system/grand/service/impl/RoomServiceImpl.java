package hotel.system.grand.service.impl;

import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.entity.RoomEntity;
import hotel.system.grand.repository.RoomRepository;
import hotel.system.grand.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    final RoomRepository roomRepository;
    final ModelMapper mapper;

    @Override
    public HttpStatus addRoom(RoomDTO roomDTO) {
        try {
            RoomEntity roomEntity = mapper.map(roomDTO, RoomEntity.class);
            roomEntity.setStatus(1);
            roomRepository.save(roomEntity);
            return HttpStatus.CREATED;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Boolean deleteRoom(Integer id) {
        Optional<RoomEntity> optionalRoomEntity = roomRepository.findById(id);
        if (optionalRoomEntity.isPresent()){
            RoomEntity roomEntity = optionalRoomEntity.get();
            roomEntity.setStatus(0);
            roomRepository.save(roomEntity);
            return true;
        }
         return false;
    }

    @Override
    public List<RoomDTO> getAll() {
        List<RoomDTO> roomDTOList=new ArrayList<>();
        roomRepository.findAll().forEach(roomEntity -> {
            if(roomEntity.getStatus()==1){
                roomDTOList.add(mapper.map(roomEntity,RoomDTO.class));
            }
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
