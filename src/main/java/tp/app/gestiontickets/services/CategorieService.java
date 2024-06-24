package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Categorie;
import tp.app.gestiontickets.repositories.CategorieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    private CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public Categorie creerCategorie(Categorie categorie) {
        return this.categorieRepository.save(categorie);
    }

    public Categorie getCategorie(long id){
        Optional<Categorie> categorie = this.categorieRepository.findById(id);
        if (categorie.isPresent()){
            return categorie.get();
        }
        return null;
    }

    public List<Categorie> categorieList() {
        return this.categorieRepository.findAll();
    }

    public void modifierCategorie(long id, Categorie categorie) {
        Categorie cAtegorie = this.getCategorie(id);
        categorie.setId(cAtegorie.getId());
        this.creerCategorie(categorie);
    }

    public void supprimerCategorie(long id) {
        this.categorieRepository.deleteById(id);
    }
}
