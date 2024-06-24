package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
