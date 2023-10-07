package hu.pcvilag.app.repositories;

import hu.pcvilag.app.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findById(long id);
}