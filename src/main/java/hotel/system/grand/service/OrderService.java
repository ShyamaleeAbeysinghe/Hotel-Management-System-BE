package hotel.system.grand.service;

import hotel.system.grand.dto.OrdersDTO;

import java.util.List;

public interface OrderService {
    void addOrder(OrdersDTO ordersDTO);

    Boolean deleteOrder(Integer id);

    List<OrdersDTO> getAll();

    List<OrdersDTO> searchOrderById(Integer id);
}
