package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Priorite;
import tp.app.gestiontickets.repositories.PrioriteRepository;

import java.util.Optional;

@Service
public class PrioriteService {
    private PrioriteRepository prioriteRepository;

    public PrioriteService(PrioriteRepository prioriteRepository) {
        this.prioriteRepository = prioriteRepository;
    }

    public Priorite creerPriorite(Priorite priorite){
        return this.prioriteRepository.save(priorite);
    }

    public Priorite getPriorite(long id) {
        Optional<Priorite> priorite = this.prioriteRepository.findById(id);
        if (priorite.isPresent())
        {
            return  priorite.get();
        }
        return null;
    }

    public void modifierPriorite(long id, Priorite priorite){
        Priorite pRiorite = this.getPriorite(id);
        priorite.setId(priorite.getId());
        this.creerPriorite(pRiorite);
    }

    public void supprimerPriorite(long id) {
        this.prioriteRepository.deleteById(id);
    }
}
