package hotel.system.grand.repository;

import hotel.system.grand.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    List<RoleEntity> findByRoleName(String roleName);
}
