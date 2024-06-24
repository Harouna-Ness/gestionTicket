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
public class BaseConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String reponse;
    private Date date;
    @ManyToOne
    private Formateur formateur;
}
