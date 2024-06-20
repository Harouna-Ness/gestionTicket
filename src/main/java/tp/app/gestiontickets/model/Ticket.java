package tp.app.gestiontickets.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
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
    private String reponses;
    private Date date;
    private Date dateReponse;
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
