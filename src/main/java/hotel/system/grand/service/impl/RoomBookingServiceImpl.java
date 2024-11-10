package hotel.system.grand.service.impl;

import hotel.system.grand.dto.RoleDTO;
import hotel.system.grand.dto.RoomBookingDTO;
import hotel.system.grand.dto.RoomBookingResponseDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.RoleEntity;
import hotel.system.grand.entity.RoomBookingEntity;
import hotel.system.grand.entity.RoomEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.repository.RoomBookingRepository;
import hotel.system.grand.repository.RoomRepository;
import hotel.system.grand.service.CustomerService;
import hotel.system.grand.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
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

    @Override
    public HttpStatus saveRoomBooking(RoomBookingDTO roomBookingDTO) {
        try {
            Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(roomBookingDTO.getCustomerId());
            Optional<RoomEntity> optionalRoomEntity = roomRepository.findById(roomBookingDTO.getRoomId());
            if (optionalRoomEntity.isPresent() && optionalCustomerEntity.isPresent()){
                RoomBookingEntity roomBookingEntity = mapper.map(roomBookingDTO, RoomBookingEntity.class);
                roomBookingEntity.setRoomEntity(optionalRoomEntity.get());
                roomBookingEntity.setCustomerEntity02(optionalCustomerEntity.get());
                roomBookingEntity.setStatus(1);
                roomBookingEntity.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                roomBookingRepository.save(roomBookingEntity);
                return HttpStatus.CREATED;
            }else{
                return HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public List<RoomBookingResponseDTO> getAllRoomBookings() {
        List<RoomBookingResponseDTO> roomBookingDTOList=new ArrayList<>();
        roomBookingRepository.findAll().forEach(roomBookingEntity -> {
            if(roomBookingEntity.getStatus()==1){
                RoomBookingResponseDTO roomBookingResponseDTO = mapper.map(roomBookingEntity, RoomBookingResponseDTO.class);
                roomBookingResponseDTO.setPrice(roomBookingEntity.getRoomEntity().getPrice());
                roomBookingDTOList.add(roomBookingResponseDTO);
            }
        });
        return roomBookingDTOList;
    }
}
