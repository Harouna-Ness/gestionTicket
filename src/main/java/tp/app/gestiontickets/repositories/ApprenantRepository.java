package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Apprenant;

public interface ApprenantRepository extends JpaRepository<Apprenant, Long> {
    Apprenant findByEmail(String email);
}

