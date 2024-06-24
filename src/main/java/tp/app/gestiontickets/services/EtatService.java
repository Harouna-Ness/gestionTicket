package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Etat;
import tp.app.gestiontickets.repositories.EtatRepository;

import java.util.Optional;

@Service
public class EtatService {
    private EtatRepository etatRepository;

    public EtatService(EtatRepository etatRepository) {
        this.etatRepository = etatRepository;
    }

    public Etat creerEtat(Etat etat){
        return this.etatRepository.save(etat);
    }

    public Etat getEtat(long id){
        Optional<Etat> etat = this.etatRepository.findById(id);
        if (etat.isPresent()) {
            return etat.get();
        }
        return null;
    }

    public void modifierEtat(long id, Etat etat) {
        Etat eTat = this.getEtat(id);
        etat.setId(eTat.getId());
        this.etatRepository.save(etat);
    }

    public Etat getEtatparLibelle(String libelle) {
        return etatRepository.findByLibelle(libelle);
    }

    public void supprimerEtat(long id){
        this.etatRepository.deleteById(id);
    }
}
