package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.BaseConnaissance;

public interface BaseConnaisanceRepository extends JpaRepository<BaseConnaissance, Long> {
}
