package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.model.Formateur;
import tp.app.gestiontickets.model.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByApprenant(Apprenant apprenant);
    List<Ticket> findAllByFormateur(Formateur formateur);

    List<Ticket> findAllByIsPrivate(Boolean isPrivate);
}
