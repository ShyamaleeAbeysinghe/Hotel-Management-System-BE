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
public class ManageRoomBookingDTO {
    private Integer id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Double total;
    private RoomDTO room;
    private CustomerDTO customer;
    private String status;
}
