package hotel.system.grand.service.impl;

import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.entity.RoomBookingEntity;
import hotel.system.grand.entity.RoomEntity;
import hotel.system.grand.repository.RoomBookingRepository;
import hotel.system.grand.repository.RoomRepository;
import hotel.system.grand.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    final ModelMapper mapper;
    @Override
    public List<RoomDTO> findAvailableRooms(String start, String end) {
        List<RoomBookingEntity> allByCheckinBetween = roomBookingRepository.findAllByCheckInBetween(LocalDate.parse(start), LocalDate.parse(end));
        List<RoomBookingEntity> allByCheckoutBetween = roomBookingRepository.findAllByCheckoutBetween(LocalDate.parse(start), LocalDate.parse(end));
        List<RoomEntity> allRooms=roomRepository.findAll();

        allRooms.forEach(roomEntity -> {
            allByCheckinBetween.forEach(roomBookingEntity -> {
                if (roomEntity.getId()==roomBookingEntity.getRoomEntity().getId()){
                    roomEntity.setStatus(0);
                }
            });
            allByCheckoutBetween.forEach(roomBookingEntity -> {
                if (roomEntity.getId()==roomBookingEntity.getRoomEntity().getId()){
                    roomEntity.setStatus(0);
                }
            });
        });


        List<RoomDTO> roomDTOList=new ArrayList<>();
        allRooms.forEach(roomEntity -> {
            if(roomEntity.getStatus()==1){
                roomDTOList.add(mapper.map(roomEntity,RoomDTO.class));
            }
        });
        return roomDTOList;
    }
}
