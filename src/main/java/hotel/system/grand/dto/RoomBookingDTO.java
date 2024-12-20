package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomBookingDTO {
    private Integer id;
    private LocalDate date;
    private Integer status;
    private Integer customerId;
    private Integer roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
