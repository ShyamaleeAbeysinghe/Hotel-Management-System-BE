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
import java.time.temporal.ChronoUnit;
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
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<RoomBookingEntity> allByCheckinBetween = roomBookingRepository.findAllByCheckInBetween(startDate, endDate);
        List<RoomBookingEntity> allByCheckoutBetween = roomBookingRepository.findAllByCheckoutBetween(startDate, endDate);
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

        long stayingDays = ChronoUnit.DAYS.between(startDate, endDate);

        List<RoomDTO> roomDTOList=new ArrayList<>();
        allRooms.forEach(roomEntity -> {
            if(roomEntity.getStatus()==1){
                RoomDTO roomDTO = mapper.map(roomEntity, RoomDTO.class);
                roomDTO.setPrice(roomEntity.getPrice()*stayingDays);
                roomDTOList.add(roomDTO);
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
                RoomEntity roomEntity = optionalRoomEntity.get();
                long stayingDays = ChronoUnit.DAYS.between(roomBookingDTO.getCheckIn(), roomBookingDTO.getCheckOut());
                double totalPrice = roomEntity.getPrice() * stayingDays;
                RoomBookingEntity roomBookingEntity = mapper.map(roomBookingDTO, RoomBookingEntity.class);
                roomBookingEntity.setRoomEntity(roomEntity);
                roomBookingEntity.setTotalRoomPrice(totalPrice);
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
                roomBookingResponseDTO.setPrice(roomBookingEntity.getTotalRoomPrice());
                roomBookingResponseDTO.setImg(roomBookingEntity.getRoomEntity().getImg());
                roomBookingDTOList.add(roomBookingResponseDTO);
            }
        });
        return roomBookingDTOList;
    }

    @Override
    public HttpStatus cancelBooking(Integer bookingId) {
        Optional<RoomBookingEntity> optionalRoomBookingEntity = roomBookingRepository.findById(bookingId);
        if (optionalRoomBookingEntity.isPresent()){
            RoomBookingEntity roomBookingEntity = optionalRoomBookingEntity.get();
            LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long difference = ChronoUnit.DAYS.between(currentDate, roomBookingEntity.getCheckIn());
            if (difference<7){
                return HttpStatus.NOT_ACCEPTABLE;
            }else{
                roomBookingEntity.setStatus(0);
                roomBookingRepository.save(roomBookingEntity);
                return HttpStatus.ACCEPTED;
            }
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
