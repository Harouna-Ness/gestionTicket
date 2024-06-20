package tp.app.gestiontickets.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date date;
    @ManyToOne
    private Ticket ticket;
    @ManyToOne
    private Formateur formateur;
    @ManyToOne
    private Apprenant apprenant;
}
