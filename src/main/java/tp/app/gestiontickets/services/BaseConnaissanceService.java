package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.BaseConnaissance;
import tp.app.gestiontickets.repositories.BaseConnaisanceRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BaseConnaissanceService {
    private BaseConnaisanceRepository baseConnaisanceRepository;

    public BaseConnaissanceService(BaseConnaisanceRepository baseConnaisanceRepository) {
        this.baseConnaisanceRepository = baseConnaisanceRepository;
    }

    public BaseConnaissance creerBaseConnai(BaseConnaissance baseConnaissance){
        baseConnaissance.setDate(new Date());
        return this.baseConnaisanceRepository.save(baseConnaissance);
    }

    public BaseConnaissance getBaseConnaissanceById(long id){
        Optional<BaseConnaissance> baseConnaissance = this.baseConnaisanceRepository.findById(id);
        if (baseConnaissance.isPresent()){
            return baseConnaissance.get();
        }
        return null;
    }

    public List<BaseConnaissance> voirBaseConnaissance(){
        return this.baseConnaisanceRepository.findAll();
    }

    public BaseConnaissance modifierBaseConnai(long id, BaseConnaissance baseConnaissance){
        BaseConnaissance bAseConnaisssance = this.getBaseConnaissanceById(id);
        if (bAseConnaisssance != null){
            baseConnaissance.setId(bAseConnaisssance.getId());
            return this.creerBaseConnai(baseConnaissance);
        }
        return null;
    }

    public void supprimerBaseConnai(long id){
        this.baseConnaisanceRepository.deleteById(id);
    }
}
