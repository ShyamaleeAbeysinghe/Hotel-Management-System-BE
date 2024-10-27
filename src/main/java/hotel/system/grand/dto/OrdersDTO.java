package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
    private Integer id;
    private String qty;
    private Double totalPrice;
    private Integer status;
}
