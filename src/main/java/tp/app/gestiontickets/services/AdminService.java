package tp.app.gestiontickets.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.model.Formateur;
import tp.app.gestiontickets.model.Role;
import tp.app.gestiontickets.repositories.AdminRepository;
import tp.app.gestiontickets.repositories.RoleRepository;
import tp.app.gestiontickets.repositories.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private ApprenantService apprenantService;
    private RoleRepository roleRepository;
    private FormateurService formateurService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, ApprenantService apprenantService, RoleRepository roleRepository, FormateurService formateurService) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.apprenantService = apprenantService;
        this.roleRepository = roleRepository;
        this.formateurService = formateurService;
        this.passwordEncoder = passwordEncoder;
    }

    public Admin createAdmin(Admin admin) {
        boolean adminExist = this.userRepository.existsByEmail(admin.getEmail());
        Role role = this.roleRepository.findRoleByLibelle("ADMIN");
        if (adminExist){
            System.out.println(String.format("Cet email existe déjà: ", admin.getEmail()));
        } else {
            admin.setRoles(Collections.singletonList(role));
            admin.setMdp(passwordEncoder.encode(admin.getMdp()));
            return this.adminRepository.save(admin);
        }
        return null;
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
        boolean emailExist = this.userRepository.existsByEmail(apprenant.getEmail());
        if (emailExist){
            System.out.println(String.format("Cet email existe déjà: ", apprenant.getEmail()));
            return null;
        }
        Admin aDmin = apprenant.getAdmin();
        Admin adminExistant = this.adminRepository.findByEmail(aDmin.getEmail());
        System.out.println("adminExistant");
        System.out.println(adminExistant);
        Role role = roleRepository.findRoleByLibelle("APPRENANT");
        System.out.println(role);

        if (adminExistant != null && role != null) {
            apprenant.setAdmin(adminExistant);
            apprenant.setRoles(Collections.singletonList(role));
            apprenant.setMdp(passwordEncoder.encode(apprenant.getMdp()));
            return this.apprenantService.create(apprenant);
        }
        System.out.println("Role ou admin n'existe pas !!");
        return null;
    }

    public Formateur creerFormateur(Formateur formateur){
        boolean emailExist = this.userRepository.existsByEmail(formateur.getEmail());
        if (emailExist){
            System.out.println(String.format("Cet email existe déjà: ", formateur.getEmail()));
            return null;
        }
        Admin aDmin = formateur.getAdmin();
        Admin adminExistant = this.adminRepository.findByEmail(aDmin.getEmail());
        Role role = roleRepository.findRoleByLibelle("FORMATEUR");

        if (adminExistant != null && role != null) {
            formateur.setAdmin(adminExistant);
            formateur.setRoles(Collections.singletonList(role));
            formateur.setMdp(passwordEncoder.encode(formateur.getMdp()));
            return this.formateurService.creerFormateur(formateur);
        }
        System.out.println("Role ou admin n'existe pas !!");
        return null;
    }

    public void supprimerApprenant(long id){
        this.apprenantService.deleteApprenant(id);
    }
    public void supprimerFormateur(long id){
        this.formateurService.supprimerFormateur(id);
    }
//    public void supprimerRole(long id){
//        this.roleService.supprimerRoleById(id);
//    }
    public void supprimerRole(long id){
        this.roleRepository.deleteById(id);
    }

    public void modifierApprenant(long id, Apprenant apprenant){
        Optional<Apprenant> aPprenant = this.apprenantService.getApprenant(id);
        if(aPprenant.isPresent()){
            Apprenant apprenant1 = aPprenant.get();
            apprenant.setId(apprenant1.getId());
            this.apprenantService.create(apprenant);
        }
    }

    public void modifierFomateur(long id, Formateur formateur){
        Optional<Formateur> fOrmateur = this.formateurService.getFormateur(id);
        if(fOrmateur.isPresent()){
            Formateur formateur1 = fOrmateur.get();
            formateur.setId(formateur1.getId());
            this.formateurService.creerFormateur(formateur);
        }
    }

    public List<Apprenant> voirApprenantCreerPar(Admin admin) {
        Admin aDmin = getAdminByEmail(admin.getEmail());
        if (aDmin != null){
            return this.apprenantService.voirApprenantCreerPar(aDmin);
        }
        return null;
    }

    public List<Formateur> voirFormateurCreerPar(Admin admin){
        Admin aDmin = getAdminByEmail(admin.getEmail());
        if (aDmin != null){
            return this.formateurService.voirFormateurCreerPar(admin);
        }
        return null;
    }
}
