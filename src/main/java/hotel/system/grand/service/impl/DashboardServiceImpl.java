package hotel.system.grand.service.impl;

import hotel.system.grand.dto.ManageRoomBookingDTO;
import hotel.system.grand.dto.OrdersDTO;
import hotel.system.grand.dto.StaffDTO;
import hotel.system.grand.entity.HallBookingEntity;
import hotel.system.grand.entity.RoomBookingEntity;
import hotel.system.grand.repository.HallBookingRepository;
import hotel.system.grand.repository.RoomBookingRepository;
import hotel.system.grand.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final RoomBookingService roomBookingService;
    private final HallBookingService hallBookingService;
    private final RoomBookingRepository roomBookingRepository;
    private final HallBookingRepository hallBookingRepository;
    private final OrderService orderService;
    private final StaffService staffService;

    @Override
    public Map<String,Object> getBookingHostoryStats() {
        Map<String,Object> response=new HashMap<>();
        List<String> months=new ArrayList<>();
        List<Integer> roomBookings=new ArrayList<>();
        for (int i=0;i<12;i++){
            LocalDate today = LocalDate.now().minusMonths(i);
            List<RoomBookingEntity> allByCheckInBetween = roomBookingRepository.findAllByCheckInBetween(today.withDayOfMonth(1), today.withDayOfMonth(today.getMonth().length(today.isLeapYear())));
            roomBookings.add(allByCheckInBetween.size());
            months.add(today.getMonth().toString());
        }
        List<Integer> hallBookings = new ArrayList<>();
        for (int i=0;i<12;i++){
            LocalDate today = LocalDate.now().minusMonths(i);
            List<HallBookingEntity> allByBookedDateBetween = hallBookingRepository.findAllByBookedDateBetween(today.withDayOfMonth(1), today.withDayOfMonth(today.getMonth().length(today.isLeapYear())));
            hallBookings.add(allByBookedDateBetween.size());
        }
        response.put("months", months);
        response.put("roomBookings", roomBookings);
        response.put("hallBookings", hallBookings);

        return response;
    }

    @Override
    public Map<String, Integer> getRoomDashboardData() {
        Map<String, Integer> response = new HashMap<>();
        List<ManageRoomBookingDTO> allRoomBookings = roomBookingService.getAllRoomBookings();
        response.put("totalBookings",allRoomBookings.size());
        List<ManageRoomBookingDTO> activeBookings=new ArrayList<>();
        allRoomBookings.forEach(manageRoomBookingDTO -> {
            if (!manageRoomBookingDTO.getStatus().equals("Cancelled")){
                activeBookings.add(manageRoomBookingDTO);
            }
        });
        response.put("activeBookings",activeBookings.size());
        List<OrdersDTO> ordersDTOList = orderService.getAll();
        List<OrdersDTO> activeOrders = new ArrayList<>();
        ordersDTOList.forEach(ordersDTO -> {
            if (!(ordersDTO.getStatus().equals("Cancelled") || ordersDTO.getStatus().equals("Delivered"))){
                activeOrders.add(ordersDTO);
            }
        });
        response.put("activeOrders",activeOrders.size());
        List<StaffDTO> allStaff = staffService.getAll();
        response.put("allStaff",allStaff.size());

        return response;
    }

    @Override
    public Map<String, Integer> getDashboardOrderData() {
        Map<String, Integer> response = new HashMap<>();
        List<OrdersDTO> allOrders = orderService.getPendingForAdmin();
        List<OrdersDTO> pendingOrders = new ArrayList<>();
        allOrders.forEach(ordersDTO -> {
            if (ordersDTO.getStatus().equals("Pending")){
                pendingOrders.add(ordersDTO);
            }
        });
        List<OrdersDTO> preparingOrders = new ArrayList<>();
        allOrders.forEach(ordersDTO -> {
            if (ordersDTO.getStatus().equals("Preparing")){
                preparingOrders.add(ordersDTO);
            }
        });
        List<OrdersDTO> deliveringOrders = new ArrayList<>();
        allOrders.forEach(ordersDTO -> {
            if (ordersDTO.getStatus().equals("Delivering")){
                deliveringOrders.add(ordersDTO);
            }
        });
        response.put("pendingOrders",pendingOrders.size());
        response.put("preparingOrders",preparingOrders.size());
        response.put("deliveringOrders",deliveringOrders.size());
        return response;
    }
}
