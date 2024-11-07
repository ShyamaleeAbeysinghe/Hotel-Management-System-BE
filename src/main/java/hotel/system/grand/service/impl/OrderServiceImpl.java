package hotel.system.grand.service.impl;

import hotel.system.grand.dto.OrdersDTO;
import hotel.system.grand.entity.OrdersEntity;
import hotel.system.grand.repository.OrderRepository;
import hotel.system.grand.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;
    final ModelMapper mapper;

    @Override
    public void addOrder(OrdersDTO ordersDTO) {
        orderRepository.save(mapper.map(ordersDTO, OrdersEntity.class));
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
}
