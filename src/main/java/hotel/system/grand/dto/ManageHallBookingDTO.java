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
public class ManageHallBookingDTO {
    private Integer id;
    private LocalDate bookDate;
    private Double total;
    private HallDTO hall;
    private CustomerDTO customer;
    private String status;
}
