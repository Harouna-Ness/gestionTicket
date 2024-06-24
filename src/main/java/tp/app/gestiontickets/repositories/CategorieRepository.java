package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
