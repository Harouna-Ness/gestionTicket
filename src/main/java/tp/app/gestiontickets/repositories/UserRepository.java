package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    boolean existsByEmail(String email);
}
