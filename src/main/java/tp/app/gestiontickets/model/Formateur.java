package tp.app.gestiontickets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Formateur extends UserEntity {

    private String specialite;
    @ManyToOne
    private Admin admin;
    @OneToMany
    private List<Notification> notifications;
    @OneToMany
    private List<Ticket> tickets;
    @OneToMany List<BaseConnaissance> baseConnaissances;
}
