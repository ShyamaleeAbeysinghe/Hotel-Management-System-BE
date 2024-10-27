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
    private String roomName;
    private Double price;
    private String roomType;
    private String description;
    private Integer status;
}
