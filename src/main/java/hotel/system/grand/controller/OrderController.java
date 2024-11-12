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
    public HttpStatus UpdateOrder(@RequestBody OrdersDTO ordersDTO){
        return orderService.updateOrder(ordersDTO);
    }

    @DeleteMapping("/delete-order/{id}")
    public Boolean deleteOrder(@PathVariable Integer id){
        return orderService.deleteOrder(id);
    }
    @GetMapping("/get-all")
    List<OrdersDTO> getAllOrders(){
        return orderService.getAll();
    }
    @GetMapping("/getAllByCustomer/{id}")
    List<OrdersDTO> getAllOrdersByCustomer(@PathVariable Integer id){
        return orderService.getAllBYCustomer(id);
    }
    @GetMapping("/getAllForChef")
    List<OrdersDTO> getAllForChef(){
        return orderService.getAllForChef();
    }
    @GetMapping("/getCompletedForChef")
    List<OrdersDTO> getCompletedForChef(){
        return orderService.getCompletedForChef();
    }
    @GetMapping("/getAllForRoomBoy")
    List<OrdersDTO> getAllForRoomBoy(){
        return orderService.getAllForRoomBoy();
    }
    @GetMapping("/getCompletedForRoomBoy")
    List<OrdersDTO> getCompletedForRoomBoy(){
        return orderService.getCompletedForRoomBoy();
    }
    @GetMapping("/getPendingForAdmin")
    List<OrdersDTO> getPendingForAdmin(){
        return orderService.getPendingForAdmin();
    }

    @GetMapping("/search-by-orderId/{id}")
    public List<OrdersDTO> searchOrderById(@PathVariable Integer id){
        return orderService.searchOrderById(id);
    }
}
