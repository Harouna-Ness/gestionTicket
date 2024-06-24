package tp.app.gestiontickets.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Admin extends UserEntity {

    @OneToMany
    private List<Apprenant> apprenant;

    @OneToMany
    private  List<Formateur> formateur;

}
