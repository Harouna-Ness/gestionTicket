package tp.app.gestiontickets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Apprenant extends UserEntity {
    @ManyToOne
    private Admin admin;
    @OneToMany
    private List<Notification> notifications;
    @OneToMany
    private List<Ticket> tickets;
}
