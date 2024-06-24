package tp.app.gestiontickets.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Ticket;
import tp.app.gestiontickets.model.TicketReponse;
import tp.app.gestiontickets.repositories.TicketReponseRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketReponseService {
    private TicketReponseRepository ticketReponseRepository;

    public List<TicketReponse> voirTicketReponse(Ticket ticket) {
        return this.ticketReponseRepository.findAllByTicket(ticket);
    }
}
