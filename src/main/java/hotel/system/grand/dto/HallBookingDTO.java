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
    private LocalDate bookedDate;
    private Integer status;
    private Integer customerId;
    private Integer hallId;


}
