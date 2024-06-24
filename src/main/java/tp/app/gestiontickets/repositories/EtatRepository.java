package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Etat;

public interface EtatRepository extends JpaRepository<Etat, Long> {
    Etat findByLibelle(String libelle);
}
