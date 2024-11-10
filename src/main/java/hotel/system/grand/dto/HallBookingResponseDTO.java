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
public class HallBookingResponseDTO {

    private Integer id;
    private LocalDate date;
    private String hallName;
    private LocalDate bookedDate;
    private Double price;
    private String img;
}
