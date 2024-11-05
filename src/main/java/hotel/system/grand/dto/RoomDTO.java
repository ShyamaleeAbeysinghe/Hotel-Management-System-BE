package hotel.system.grand.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Integer id;
    private String img;
    private String roomName;
    private Integer roomNo;
    private Double price;
    private String size;
    private Integer beds;
    private String description;
    private Integer status;
}
