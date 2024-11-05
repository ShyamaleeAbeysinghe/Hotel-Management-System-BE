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
    private String img;
    private String hallName;
    private Double price;
    private Integer chairs;
    private Integer tables;
    private Integer status;

}
