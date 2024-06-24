package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.repositories.ApprenantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ApprenantService {

    private ApprenantRepository apprenantRepository;

    public ApprenantService(ApprenantRepository apprenantRepository) {

        this.apprenantRepository = apprenantRepository;
    }
    public Apprenant create(Apprenant apprenant) {

        return this.apprenantRepository.save(apprenant);
    }

    public List<Apprenant> voirApprenant(){
        return this.apprenantRepository.findAll();
    }

    public List<Apprenant> voirApprenantCreerPar(Admin admin){
        return this.apprenantRepository.findAllByAdmin(admin);
    }

    public Optional<Apprenant> getApprenant(Long id){

        return this.apprenantRepository.findById(id);
    }

    public Apprenant getApprenantByEmail(String email){

        return this.apprenantRepository.findByEmail(email);
    }

    public void deleteApprenant(long id){

        this.apprenantRepository.deleteById(id);
    }
}
