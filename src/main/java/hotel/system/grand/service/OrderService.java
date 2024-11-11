package hotel.system.grand.service;

import hotel.system.grand.dto.OrdersDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface OrderService {
    HttpStatus addOrder(OrdersDTO ordersDTO);

    Boolean deleteOrder(Integer id);

    List<OrdersDTO> getAll();

    List<OrdersDTO> searchOrderById(Integer id);
}
