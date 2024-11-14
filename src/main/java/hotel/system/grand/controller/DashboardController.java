package hotel.system.grand.controller;

import hotel.system.grand.dto.CustomerDTO;
import hotel.system.grand.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {
    final DashboardService dashboardService;
    @GetMapping("/get-history")
    public Map<String,Object> getAllCustomer(){
        return dashboardService.getBookingHostoryStats();
    }

    @GetMapping("/getRoomDashboardData")
    public Map<String,Integer> getRoomDashboardData(){
        return dashboardService.getRoomDashboardData();
    }

    @GetMapping("/getDashboardOrderData")
    public Map<String,Integer> getDashboardOrderData(){
        return dashboardService.getDashboardOrderData();
    }
}
