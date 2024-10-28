package hotel.system.grand.repository;

import hotel.system.grand.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity,Integer> {
}
