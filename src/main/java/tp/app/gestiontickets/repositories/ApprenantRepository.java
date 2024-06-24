package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Apprenant;

import java.util.List;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    Apprenant findByEmail(String email);
    List<Apprenant> findAllByAdmin(Admin admin);
}

