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
public class RoomBookingResponseDTO {
    private Integer id;
    private String roomName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Integer roomNo;
    private Double price;
}
