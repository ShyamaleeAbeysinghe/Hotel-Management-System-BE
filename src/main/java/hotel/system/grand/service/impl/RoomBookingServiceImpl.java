package hotel.system.grand.service.impl;

import hotel.system.grand.dto.*;
import hotel.system.grand.entity.CustomerEntity;
import hotel.system.grand.entity.OrdersEntity;
import hotel.system.grand.entity.RoomBookingEntity;
import hotel.system.grand.entity.RoomEntity;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.repository.OrderRepository;
import hotel.system.grand.repository.RoomBookingRepository;
import hotel.system.grand.repository.RoomRepository;
import hotel.system.grand.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class RoomBookingServiceImpl implements RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    final ModelMapper mapper;

    @Override
    public List<RoomDTO> findAvailableRooms(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<RoomBookingEntity> allByCheckinBetween = roomBookingRepository.findAllByCheckInBetween(startDate, endDate);
        List<RoomBookingEntity> allByCheckoutBetween = roomBookingRepository.findAllByCheckoutBetween(startDate, endDate);
        List<RoomEntity> allRooms = roomRepository.findAll();

        allRooms.forEach(roomEntity -> {
            allByCheckinBetween.forEach(roomBookingEntity -> {
                if (roomEntity.getId() == roomBookingEntity.getRoomEntity().getId()) {
                    roomEntity.setStatus(0);
                }
            });
            allByCheckoutBetween.forEach(roomBookingEntity -> {
                if (roomEntity.getId() == roomBookingEntity.getRoomEntity().getId()) {
                    roomEntity.setStatus(0);
                }
            });
        });

        long stayingDays = ChronoUnit.DAYS.between(startDate, endDate);

        List<RoomDTO> roomDTOList = new ArrayList<>();
        allRooms.forEach(roomEntity -> {
            if (roomEntity.getStatus() == 1) {
                RoomDTO roomDTO = mapper.map(roomEntity, RoomDTO.class);
                roomDTO.setPrice(roomEntity.getPrice() * stayingDays);
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
            if (optionalRoomEntity.isPresent() && optionalCustomerEntity.isPresent()) {
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
            } else {
                return HttpStatus.NOT_FOUND;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public List<RoomBookingResponseDTO> getUserRoomBookings(Integer userId) {
        List<RoomBookingResponseDTO> roomBookingDTOList = new ArrayList<>();
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(userId);
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            roomBookingRepository.findAllByCustomerEntity02(customerEntity).forEach(roomBookingEntity -> {
                if (roomBookingEntity.getStatus() == 1) {
                    RoomBookingResponseDTO roomBookingResponseDTO = mapper.map(roomBookingEntity, RoomBookingResponseDTO.class);
                    roomBookingResponseDTO.setPrice(roomBookingEntity.getTotalRoomPrice());
                    roomBookingResponseDTO.setImg(roomBookingEntity.getRoomEntity().getImg());
                    roomBookingDTOList.add(roomBookingResponseDTO);
                }
            });
        }

        return roomBookingDTOList;
    }

    @Override
    public HttpStatus cancelBooking(Integer bookingId) {
        Optional<RoomBookingEntity> optionalRoomBookingEntity = roomBookingRepository.findById(bookingId);
        if (optionalRoomBookingEntity.isPresent()) {
            RoomBookingEntity roomBookingEntity = optionalRoomBookingEntity.get();
            LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long difference = ChronoUnit.DAYS.between(currentDate, roomBookingEntity.getCheckIn());
            if (difference < 7) {
                return HttpStatus.NOT_ACCEPTABLE;
            } else {
                roomBookingEntity.setStatus(0);
                roomBookingRepository.save(roomBookingEntity);
                return HttpStatus.ACCEPTED;
            }
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    @Override
    public List<ManageBookingDTO> getAllRoomBookings() {
        List<ManageBookingDTO> bookingDTOList = new ArrayList<>();
        roomBookingRepository.findAll().forEach(roomBookingEntity -> {
            roomBookingEntity.getCustomerEntity02().setPassword("");
            roomBookingEntity.getCustomerEntity02().setUserName("");
            ManageBookingDTO manageBookingDTO = mapper.map(roomBookingEntity, ManageBookingDTO.class);
            if (roomBookingEntity.getStatus() == 0) {
                manageBookingDTO.setStatus("Cancelled");
            } else if (roomBookingEntity.getStatus() == 1) {
                manageBookingDTO.setStatus("Pending");
            } else if (roomBookingEntity.getStatus() == 2) {
                manageBookingDTO.setStatus("CheckIn");
            } else if (roomBookingEntity.getStatus() == 3) {
                manageBookingDTO.setStatus("CheckOut");
            }
            bookingDTOList.add(manageBookingDTO);
        });
        return bookingDTOList;
    }

    @Override
    public Map<String, String> customerCheckIn(Integer bookingId) {
        Map<String, String> response = new HashMap<>();
        Optional<RoomBookingEntity> optionalRoomBookingEntity = roomBookingRepository.findById(bookingId);
        if (optionalRoomBookingEntity.isPresent()) {
            LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            RoomBookingEntity roomBookingEntity = optionalRoomBookingEntity.get();
            if (currentDate.equals(roomBookingEntity.getCheckIn())
                    || (currentDate.isAfter(roomBookingEntity.getCheckIn()) && currentDate.isBefore(roomBookingEntity.getCheckout()))) {
                roomBookingEntity.setStatus(2);
                roomBookingRepository.save(roomBookingEntity);
                response.put("status", "success");
            } else {
                response.put("status", "failed");
                response.put("reason", "invalid date");
            }
            return response;

        }
        response.put("status", "failed");
        response.put("reason", "not found");

        return response;
    }

    @Override
    public HttpStatus customerCheckOut(Integer bookingId) {
        try {
            Optional<RoomBookingEntity> optionalRoomBookingEntity = roomBookingRepository.findById(bookingId);
            if (optionalRoomBookingEntity.isPresent()) {
                LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                RoomBookingEntity roomBookingEntity = optionalRoomBookingEntity.get();
                roomBookingEntity.setStatus(3);
                roomBookingRepository.save(roomBookingEntity);
                return HttpStatus.ACCEPTED;

            }
            return HttpStatus.NOT_FOUND;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Double getRoomBookingTotal(Integer bookingId) {
        AtomicReference<Double> totalPrice= new AtomicReference<>(0.0);
        Optional<RoomBookingEntity> optionalRoomBookingEntity = roomBookingRepository.findById(bookingId);
        if (optionalRoomBookingEntity.isPresent()) {
            RoomBookingEntity roomBookingEntity = optionalRoomBookingEntity.get();
            List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.getAllOrdersByRoomBookingEntity(roomBookingEntity);
            ordersByRoomBookingEntity.forEach(ordersEntity -> {
                totalPrice.updateAndGet(v -> v + ordersEntity.getTotalPrice());
            });
            totalPrice.updateAndGet(v -> v + roomBookingEntity.getTotalRoomPrice());
        }
        return totalPrice.get();
    }

    @Override
    public Boolean isCustomerCheckedIn(Integer customerId) {
        AtomicBoolean isCustomerCheckedIn= new AtomicBoolean(false);
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerId);
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            List<RoomBookingEntity> roomBookingEntities = roomBookingRepository.findAllByCustomerEntity02(customerEntity);
            roomBookingEntities.forEach(roomBookingEntity -> {
                if (roomBookingEntity.getStatus()==2){
                    isCustomerCheckedIn.set(true);
                }
            });
        }
        return isCustomerCheckedIn.get();
    }
}
