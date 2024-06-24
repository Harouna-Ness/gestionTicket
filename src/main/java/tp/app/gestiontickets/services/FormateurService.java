package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Formateur;
import tp.app.gestiontickets.repositories.FormateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurService{
    private FormateurRepository formateurRepository;

    public FormateurService(FormateurRepository formateurRepository) {
        this.formateurRepository = formateurRepository;
    }

    public Formateur creerFormateur(Formateur formateur) {
        return this.formateurRepository.save(formateur);
    }

    public Optional<Formateur> getFormateur(long id) {
        return this.formateurRepository.findById(id);
    }

    public Formateur recupererFormateurByEmail(String email) {
        return this.formateurRepository.findByEmail(email);
    }

    public void supprimerFormateur(long id) {
        this.formateurRepository.deleteById(id);
    }

    public List<Formateur> voirFormateurCreerPar(Admin admin){
        return this.formateurRepository.findAllByAdmin(admin);
    }
}
