package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResturentMenuDTO {
    private Integer id;
    private String foodName;
    private Double price;
    private String description;
    private Integer status;
}
