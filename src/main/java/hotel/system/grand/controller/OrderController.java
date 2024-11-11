package hotel.system.grand.controller;

import hotel.system.grand.dto.OrdersDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.service.OrderService;
import hotel.system.grand.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    final OrderService orderService;

    @PostMapping("/add-order")
    public HttpStatus addOrder(@RequestBody OrdersDTO ordersDTO){
        return orderService.addOrder(ordersDTO);
    }

    @PutMapping("/update-order")
    public void UpdateOrder(@RequestBody OrdersDTO ordersDTO){
        orderService.addOrder(ordersDTO);
    }

    @DeleteMapping("/delete-order")
    public Boolean deleteOrder(Integer id){
        return orderService.deleteOrder(id);
    }
    @GetMapping("/get-all")
    List<OrdersDTO> getAllOrders(){
        return orderService.getAll();
    }

    @GetMapping("/search-by-orderId/{id}")
    public List<OrdersDTO> searchOrderById(@PathVariable Integer id){
        return orderService.searchOrderById(id);
    }
}
