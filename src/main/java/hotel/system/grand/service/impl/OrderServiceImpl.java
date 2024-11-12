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
            OrdersEntity ordersEntity = new OrdersEntity();
            RoomBookingEntity roomBooking = getRoomBooking(ordersDTO.getCustomerId());
            ordersEntity.setRoomBookingEntity(roomBooking);
            ordersEntity.setTotalPrice(ordersDTO.getTotalPrice());
            ordersEntity.setStatus(1);
            OrdersEntity savedOrder = orderRepository.save(ordersEntity);
            for (FoodDTO food : ordersDTO.getFoods()) {
                Optional<ResturentMenuEntity> optionalResturentMenuEntity = resturentMenuRepository.findById(food.getId());
                if (optionalResturentMenuEntity.isPresent()) {
                    ResturentMenuEntity resturentMenuEntity = optionalResturentMenuEntity.get();
                    ResturentManuHasOrderEntity resturentManuHasOrderEntity = new ResturentManuHasOrderEntity();
                    resturentManuHasOrderEntity.setResturentMenuEntity(resturentMenuEntity);
                    resturentManuHasOrderEntity.setOrdersEntity(savedOrder);
                    resturentManuHasOrderEntity.setQty(food.getQty());
                    resturantHasOrderRepository.save(resturentManuHasOrderEntity);
                }

            }
            return HttpStatus.CREATED;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Boolean deleteOrder(Integer id) {
        Optional<OrdersEntity> optionalOrdersEntity = orderRepository.findById(id);
        if (optionalOrdersEntity.isPresent()) {
            OrdersEntity ordersEntity = optionalOrdersEntity.get();
            ordersEntity.setStatus(0);
            orderRepository.save(ordersEntity);
            return true;
        }

        return false;
    }

    @Override
    public List<OrdersDTO> getAll() {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        orderRepository.findAll().forEach(ordersEntity -> {
            ordersDTOList.add(mapper.map(ordersEntity, OrdersDTO.class));
        });
        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getAllBYCustomer(Integer customerId) {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        RoomBookingEntity roomBookingEntity = getRoomBooking(customerId);
        List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.getAllOrdersByRoomBookingEntity(roomBookingEntity);
        ordersByRoomBookingEntity.forEach(ordersEntity -> {
            List<ResturentManuHasOrderEntity> resturentManuHasOrderEntityList = resturantHasOrderRepository.findAllByOrdersEntity(ordersEntity);
            List<FoodDTO> foodDTOList = new ArrayList<>();
            resturentManuHasOrderEntityList.forEach(resturentManuHasOrderEntity -> {
                FoodDTO foodDTO = mapper.map(resturentManuHasOrderEntity, FoodDTO.class);
                foodDTO.setName(resturentManuHasOrderEntity.getResturentMenuEntity().getFoodName());
                foodDTO.setPrice(resturentManuHasOrderEntity.getResturentMenuEntity().getPrice());
                foodDTOList.add(foodDTO);
            });
            OrdersDTO ordersDTO = mapper.map(ordersEntity, OrdersDTO.class);
            ordersDTO.setFoods(foodDTOList.toArray(new FoodDTO[foodDTOList.size()]));
            if (ordersEntity.getStatus() == 0) {
                ordersDTO.setStatus("Cancelled");
            } else if (ordersEntity.getStatus() == 1) {
                ordersDTO.setStatus("Pending");
            } else if (ordersEntity.getStatus() == 2) {
                ordersDTO.setStatus("Preparing");
            } else if (ordersEntity.getStatus() == 3) {
                ordersDTO.setStatus("Delivering");
            } else if (ordersEntity.getStatus() == 4) {
                ordersDTO.setStatus("Delivered");
            }
            ordersDTOList.add(ordersDTO);
        });

        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getAllForChef() {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.findAll();
        ordersByRoomBookingEntity.forEach(ordersEntity -> {
            if (ordersEntity.getStatus() == 1 || ordersEntity.getStatus() == 2) {
                List<ResturentManuHasOrderEntity> resturentManuHasOrderEntityList = resturantHasOrderRepository.findAllByOrdersEntity(ordersEntity);
                List<FoodDTO> foodDTOList = new ArrayList<>();
                resturentManuHasOrderEntityList.forEach(resturentManuHasOrderEntity -> {
                    FoodDTO foodDTO = mapper.map(resturentManuHasOrderEntity, FoodDTO.class);
                    foodDTO.setName(resturentManuHasOrderEntity.getResturentMenuEntity().getFoodName());
                    foodDTO.setPrice(resturentManuHasOrderEntity.getResturentMenuEntity().getPrice());
                    foodDTOList.add(foodDTO);
                });
                OrdersDTO ordersDTO = mapper.map(ordersEntity, OrdersDTO.class);
                ordersDTO.setFoods(foodDTOList.toArray(new FoodDTO[foodDTOList.size()]));
                if (ordersEntity.getStatus() == 1) {
                    ordersDTO.setStatus("Pending");
                } else if (ordersEntity.getStatus() == 2) {
                    ordersDTO.setStatus("Preparing");
                }
                ordersDTOList.add(ordersDTO);
            }

        });

        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getCompletedForChef() {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.findAll();
        ordersByRoomBookingEntity.forEach(ordersEntity -> {
            if (ordersEntity.getStatus() == 3 || ordersEntity.getStatus() == 4) {
                List<ResturentManuHasOrderEntity> resturentManuHasOrderEntityList = resturantHasOrderRepository.findAllByOrdersEntity(ordersEntity);
                List<FoodDTO> foodDTOList = new ArrayList<>();
                resturentManuHasOrderEntityList.forEach(resturentManuHasOrderEntity -> {
                    FoodDTO foodDTO = mapper.map(resturentManuHasOrderEntity, FoodDTO.class);
                    foodDTO.setName(resturentManuHasOrderEntity.getResturentMenuEntity().getFoodName());
                    foodDTO.setPrice(resturentManuHasOrderEntity.getResturentMenuEntity().getPrice());
                    foodDTOList.add(foodDTO);
                });
                OrdersDTO ordersDTO = mapper.map(ordersEntity, OrdersDTO.class);
                ordersDTO.setFoods(foodDTOList.toArray(new FoodDTO[foodDTOList.size()]));
                if (ordersEntity.getStatus() == 3) {
                    ordersDTO.setStatus("Delivering");
                } else if (ordersEntity.getStatus() == 4) {
                    ordersDTO.setStatus("Delivered");
                }
                ordersDTOList.add(ordersDTO);
            }

        });

        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getAllForRoomBoy() {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.findAll();
        ordersByRoomBookingEntity.forEach(ordersEntity -> {
            if (ordersEntity.getStatus() == 3) {
                List<ResturentManuHasOrderEntity> resturentManuHasOrderEntityList = resturantHasOrderRepository.findAllByOrdersEntity(ordersEntity);
                List<FoodDTO> foodDTOList = new ArrayList<>();
                resturentManuHasOrderEntityList.forEach(resturentManuHasOrderEntity -> {
                    FoodDTO foodDTO = mapper.map(resturentManuHasOrderEntity, FoodDTO.class);
                    foodDTO.setName(resturentManuHasOrderEntity.getResturentMenuEntity().getFoodName());
                    foodDTO.setPrice(resturentManuHasOrderEntity.getResturentMenuEntity().getPrice());
                    foodDTOList.add(foodDTO);
                });
                OrdersDTO ordersDTO = mapper.map(ordersEntity, OrdersDTO.class);
                ordersDTO.setFoods(foodDTOList.toArray(new FoodDTO[foodDTOList.size()]));
                if (ordersEntity.getStatus() == 3) {
                    ordersDTO.setStatus("Delivering");
                }
                ordersDTOList.add(ordersDTO);
            }

        });

        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getCompletedForRoomBoy() {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.findAll();
        ordersByRoomBookingEntity.forEach(ordersEntity -> {
            if (ordersEntity.getStatus() == 4) {
                List<ResturentManuHasOrderEntity> resturentManuHasOrderEntityList = resturantHasOrderRepository.findAllByOrdersEntity(ordersEntity);
                List<FoodDTO> foodDTOList = new ArrayList<>();
                resturentManuHasOrderEntityList.forEach(resturentManuHasOrderEntity -> {
                    FoodDTO foodDTO = mapper.map(resturentManuHasOrderEntity, FoodDTO.class);
                    foodDTO.setName(resturentManuHasOrderEntity.getResturentMenuEntity().getFoodName());
                    foodDTO.setPrice(resturentManuHasOrderEntity.getResturentMenuEntity().getPrice());
                    foodDTOList.add(foodDTO);
                });
                OrdersDTO ordersDTO = mapper.map(ordersEntity, OrdersDTO.class);
                ordersDTO.setFoods(foodDTOList.toArray(new FoodDTO[foodDTOList.size()]));
                if (ordersEntity.getStatus() == 4) {
                    ordersDTO.setStatus("Delivered");
                }
                ordersDTOList.add(ordersDTO);
            }

        });

        return ordersDTOList;
    }

    @Override
    public List<OrdersDTO> getPendingForAdmin() {
        List<OrdersDTO> ordersDTOList = new ArrayList<>();
        List<OrdersEntity> ordersByRoomBookingEntity = orderRepository.findAll();
        ordersByRoomBookingEntity.forEach(ordersEntity -> {
            if (ordersEntity.getStatus() != 4 ) {
                List<ResturentManuHasOrderEntity> resturentManuHasOrderEntityList = resturantHasOrderRepository.findAllByOrdersEntity(ordersEntity);
                List<FoodDTO> foodDTOList = new ArrayList<>();
                resturentManuHasOrderEntityList.forEach(resturentManuHasOrderEntity -> {
                    FoodDTO foodDTO = mapper.map(resturentManuHasOrderEntity, FoodDTO.class);
                    foodDTO.setName(resturentManuHasOrderEntity.getResturentMenuEntity().getFoodName());
                    foodDTO.setPrice(resturentManuHasOrderEntity.getResturentMenuEntity().getPrice());
                    foodDTOList.add(foodDTO);
                });
                OrdersDTO ordersDTO = mapper.map(ordersEntity, OrdersDTO.class);
                ordersDTO.setFoods(foodDTOList.toArray(new FoodDTO[foodDTOList.size()]));
                if (ordersEntity.getStatus() == 1) {
                    ordersDTO.setStatus("Pending");
                } else if (ordersEntity.getStatus() == 2) {
                    ordersDTO.setStatus("Preparing");
                }else if (ordersEntity.getStatus() == 3) {
                    ordersDTO.setStatus("Delivering");
                }
                ordersDTOList.add(ordersDTO);
            }

        });

        return ordersDTOList;
    }

    @Override
    public HttpStatus updateOrder(OrdersDTO ordersDTO) {
       try{
           Optional<OrdersEntity> optionalOrdersEntity = orderRepository.findById(ordersDTO.getId());
           if (optionalOrdersEntity.isPresent()){
               OrdersEntity ordersEntity = optionalOrdersEntity.get();
               if (ordersDTO.getStatus().equals("Pending")){
                   ordersEntity.setStatus(2);
               }else if (ordersDTO.getStatus().equals("Preparing")){
                   ordersEntity.setStatus(3);
               }else if (ordersDTO.getStatus().equals("Delivering")){
                   ordersEntity.setStatus(4);
               }
               orderRepository.save(ordersEntity);
               return HttpStatus.ACCEPTED;
           }
           return HttpStatus.NOT_FOUND;
       }catch (Exception e){
           e.printStackTrace();
           return HttpStatus.INTERNAL_SERVER_ERROR;
       }
    }

    @Override
    public List<OrdersDTO> searchOrderById(Integer id) {

        return List.of();
    }

    private RoomBookingEntity getRoomBooking(Integer customerId) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customerId);
        AtomicReference<RoomBookingEntity> roomBooking = new AtomicReference<>();
        if (optionalCustomerEntity.isPresent()) {
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            List<RoomBookingEntity> roomBookingEntities = roomBookingRepository.findAllByCustomerEntity02(customerEntity);
            roomBookingEntities.forEach(roomBookingEntity -> {
                if (roomBookingEntity.getStatus() == 2) {
                    roomBooking.set(roomBookingEntity);
                }
            });
        }
        return roomBooking.get();
    }
}
