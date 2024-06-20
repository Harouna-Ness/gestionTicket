package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.repositories.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private ApprenantService apprenantService;

    public AdminService(AdminRepository adminRepository, ApprenantService apprenantService) {
        this.adminRepository = adminRepository;
        this.apprenantService = apprenantService;
    }

    public Admin createAdmin() {
        Admin admin = new Admin();
        admin.setNom("Ness");
        admin.setPrenom("Harouna");
        admin.setEmail("harouna@gmail.com");
        admin.setMdp("mdp");
        return this.adminRepository.save(admin);
    }

    public List<Admin> voirAdmin(){
        return this.adminRepository.findAll();
    }

    public Optional<Admin> getAdmin(Long id){
        return this.adminRepository.findById(id);
    }

    public Admin getAdminByEmail(String email){
        return this.adminRepository.findByEmail(email);
    }

    public Apprenant creerApprenant(Apprenant apprenant){
        Admin aDmin = apprenant.getAdmin();
        Admin adminExistant = this.adminRepository.findByEmail(aDmin.getEmail());

        if (adminExistant != null) {
            apprenant.setAdmin(adminExistant);
            return this.apprenantService.create(apprenant);
        }
        return null;
    }

    public void supprimerApprenant(long id){
        this.apprenantService.deleteApprenant(id);
    }
}
