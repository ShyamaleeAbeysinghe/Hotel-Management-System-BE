package hotel.system.grand.repository;

import hotel.system.grand.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HallRepository extends JpaRepository<HallEntity,Integer> {
    List<HallEntity> findByHallName(String hallName);
}
