package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Formateur;

import java.util.List;

public interface FormateurRepository extends JpaRepository<Formateur, Long> {
    Formateur findByEmail(String email);
List<Formateur> findAllByAdmin(Admin admin);
}
