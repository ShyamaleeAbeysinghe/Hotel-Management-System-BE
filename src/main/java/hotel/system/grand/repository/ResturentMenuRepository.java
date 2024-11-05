package hotel.system.grand.repository;

import hotel.system.grand.entity.ResturentMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResturentMenuRepository extends JpaRepository<ResturentMenuEntity,Integer> {
    List<ResturentMenuEntity> findByFoodName(String foodName);
}
