package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HallDTO {

    private  Integer id;
    private String hallName;
    private String capasity;
    private Double price;
    private String description;
    private Integer status;

}
