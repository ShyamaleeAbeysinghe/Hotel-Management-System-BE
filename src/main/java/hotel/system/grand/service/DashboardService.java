package hotel.system.grand.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Object> getBookingHostoryStats();

    Map<String,Integer> getRoomDashboardData();
    Map<String,Integer> getDashboardOrderData();
}
