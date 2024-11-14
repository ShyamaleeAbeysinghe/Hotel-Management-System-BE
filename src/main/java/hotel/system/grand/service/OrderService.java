package hotel.system.grand.service;

import hotel.system.grand.dto.OrdersDTO;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public interface OrderService {
    HttpStatus addOrder(OrdersDTO ordersDTO);

    Boolean deleteOrder(Integer id);

    List<OrdersDTO> getAll();
    List<OrdersDTO> getAllBYCustomer(Integer customerId);
    List<OrdersDTO> getAllForChef();
    List<OrdersDTO> getCompletedForChef();
    List<OrdersDTO> getAllForRoomBoy();
    List<OrdersDTO> getCompletedForRoomBoy();
    List<OrdersDTO> getPendingForAdmin();
    HttpStatus updateOrder(OrdersDTO ordersDTO);

    List<OrdersDTO> searchOrderById(Integer id);
}
