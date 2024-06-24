package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.model.Formateur;
import tp.app.gestiontickets.model.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByFormateur(Formateur formateur);
    List<Notification> findAllByApprenant(Apprenant apprenant);
}
