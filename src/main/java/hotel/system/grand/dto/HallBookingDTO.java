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
public class HallBookingDTO {
    private Integer id;
    private LocalDate date;
    private Integer status;
    private String customerName;


}
