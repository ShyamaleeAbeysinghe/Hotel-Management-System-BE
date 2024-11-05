package hotel.system.grand.repository;

import hotel.system.grand.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
    List<RoomEntity> findByRoomName(String roomName);

    List<RoomEntity> findByRoomNo(Integer roomNo);
}
