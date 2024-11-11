package hotel.system.grand.service.impl;

import hotel.system.grand.dto.FoodDTO;
import hotel.system.grand.dto.OrdersDTO;
import hotel.system.grand.entity.*;
import hotel.system.grand.repository.*;
import hotel.system.grand.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final ResturentMenuRepository resturentMenuRepository;
    final ResturantHasOrderRepository resturantHasOrderRepository;
    final OrderRepository orderRepository;
    final RoomBookingRepository roomBookingRepository;
    final CustomerRepository customerRepository;
    final ModelMapper mapper;

    @Override
    @Transactional
    public HttpStatus addOrder(OrdersDTO ordersDTO) {
        try {
            OrdersEntity ordersEntity=new OrdersEntity();
            RoomBookingEntity roomBooking = getRoomBooking(ordersDTO.getCustomerId());
            ordersEntity.setRoomBookingEntity(roomBooking);
            ordersEntity.setTotalPrice(ordersDTO.getTotalPrice());
            ordersEntity.setStatus(1);
            OrdersEntity savedOrder = orderRepository.save(ordersEntity);
            for (FoodDTO food : ordersDTO.getFoods()) {
                Optional<ResturentMenuEntity> optionalResturentMenuEntity = resturentMenuRepository.findById(food.getId());
                if (optionalResturentMenuEntity.isPresent()){
                    ResturentMenuEntity resturentMenuEntity = optionalResturentMenuEntity.get();
                    ResturentManuHasOrderEntity resturentManuHasOrderEntity=new ResturentManuHasOrderEntity();
                    resturentManuHasOrderEntity.setResturentMenuEntity(resturentMenuEntity);
                    resturentManuHasOrderEntity.setOrdersEntity(savedOrder);
                    resturentManuHasOrderEntity.setQty(food.getQty());
                    resturantHasOrderRepository.save(resturentManuHasOrderEntity);
                }

            }
            return HttpStatus.CREATED;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Boolean deleteOrder(Integer id) {
        orderRepository.deleteById(id);
        return true;
    }

    @Override
    public List<OrdersDTO> getAll() {
        List<OrdersDTO> ordersDTOList=new ArrayList<>();
        orderRepository.findAll().forEach(ordersEntity -> {
            ordersDTOList.add(mapper.map(ordersEntity,OrdersDTO.class));
        });
        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> searchOrderById(Integer id) {
       
        return List.of();
    }

    private RoomBookingEntity getRoomBooking(Integer customerId) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerId);
        AtomicReference<RoomBookingEntity> roomBooking=new AtomicReference<>();
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            List<RoomBookingEntity> roomBookingEntities = roomBookingRepository.findAllByCustomerEntity02(customerEntity);
            roomBookingEntities.forEach(roomBookingEntity -> {
                if (roomBookingEntity.getStatus()==2){
                    roomBooking.set(roomBookingEntity);
                }
            });
        }
        return roomBooking.get();
    }
}
