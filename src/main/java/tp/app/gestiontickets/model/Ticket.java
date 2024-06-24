package tp.app.gestiontickets.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenu;
    private String titre;
    private Date date;
    private boolean isPrivate;
    @OneToMany
    private List<TicketReponse> reponses;
    @ManyToOne
    private Apprenant apprenant;
    @ManyToOne
    private Formateur formateur;
    @ManyToOne
    private Etat etat;
    @ManyToOne
    private Priorite priorite;
    @ManyToOne
    private Categorie categorie;
    @OneToMany
    private List<Notification> notifications;

}
