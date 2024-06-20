package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
