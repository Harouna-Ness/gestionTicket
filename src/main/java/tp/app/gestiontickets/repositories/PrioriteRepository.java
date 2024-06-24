package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Priorite;

public interface PrioriteRepository extends JpaRepository<Priorite, Long> {
}
