package tp.app.gestiontickets.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class TicketReponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date = new Date();
    private String contenu;
    @ManyToOne
    private Ticket ticket;
    @ManyToOne
    private UserEntity user;
}
