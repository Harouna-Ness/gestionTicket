package tp.app.gestiontickets.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp.app.gestiontickets.model.Ticket;
import tp.app.gestiontickets.model.TicketReponse;

import java.util.List;

public interface TicketReponseRepository extends JpaRepository<TicketReponse, Long> {

    List<TicketReponse> findAllByTicket(Ticket ticket);
}
