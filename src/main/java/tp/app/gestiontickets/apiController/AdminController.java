package tp.app.gestiontickets.apiController;

import org.springframework.web.bind.annotation.*;
import tp.app.gestiontickets.model.*;
import tp.app.gestiontickets.repositories.FormateurRepository;
import tp.app.gestiontickets.services.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private RoleService roleService;
    private FormateurRepository formateurRepository;
    private CategorieService categorieService;
    private EtatService etatService;
    private PrioriteService prioriteService;

    public AdminController(AdminService adminService, RoleService roleService, CategorieService categorieService, EtatService etatService, PrioriteService prioriteService) {
        this.adminService = adminService;
        this.roleService = roleService;
        this.categorieService = categorieService;
        this.etatService = etatService;
        this.prioriteService = prioriteService;
    }

    @GetMapping
    public List<Admin> getAdminList(){
        return this.adminService.voirAdmin();
    }

    @GetMapping("/voirApprenantCreerPar")
    public List<Apprenant> voirApprenantCreerPar(@RequestBody Admin admin){
        return this.adminService.voirApprenantCreerPar(admin);
    }

    @GetMapping("/voirFormateurCreerPar")
    public List<Formateur> voirFormateurCreerPar(@RequestBody Admin admin){
        return this.adminService.voirFormateurCreerPar(admin);
    }

    @PostMapping
    public Admin creerAdmin(@RequestBody Admin admin) {
        return this.adminService.createAdmin(admin);
    }

    @PostMapping("/creerApprenant")
    public Apprenant creerApprenant(@RequestBody Apprenant apprenant) {
        return this.adminService.creerApprenant(apprenant);
    }

    @PostMapping("/creerFormateur")
    public Formateur creerFormateur(@RequestBody Formateur formateur) {
        return this.adminService.creerFormateur(formateur);
    }

    @PostMapping("/creerCategorie")
    public Categorie creerCategorie(@RequestBody Categorie categorie) {
        return this.categorieService.creerCategorie(categorie);
    }

    @PostMapping("/creerEtat")
    public Etat creerEtat(@RequestBody Etat etat) {
        return this.etatService.creerEtat(etat);
    }

    @PostMapping("/creerPriorite")
    public Priorite creerPriorite(@RequestBody Priorite priorite){
        return this.prioriteService.creerPriorite(priorite);
    }

    @DeleteMapping("/supprimerApprenant/{id}")
    public void supprimerApprenant(@PathVariable long id) {
        this.adminService.supprimerApprenant(id);
    }

    @DeleteMapping("/supprimerFormateur/{id}")
    public void supprimerFormateur(@PathVariable long id) {
        this.adminService.supprimerFormateur(id);
    }

    @DeleteMapping("/supprimerRole/{id}")
    public void supprimerRole(@PathVariable long id) {
        this.adminService.supprimerRole(id);
    }

    @PostMapping("/creerRole")
    public Role creerRole(@RequestBody Role role) {
        return this.roleService.creerRole(role);
    }

    @GetMapping("/roles")
    public List<Role> recupererRoles(){
        return this.roleService.recupererRoles();
    }

    @GetMapping("/Categories")
    public List<Categorie> recuppererCategorie() {
        return this.categorieService.categorieList();
    }

    @PutMapping("/modifierApprenant/{id}")
    public void modifierApprenant(@PathVariable long id, @RequestBody Apprenant apprenant){
        this.adminService.modifierApprenant(id, apprenant);
    }

    @PutMapping("/modifierFormateur/{id}")
    public void modifierFormateur(@PathVariable long id, @RequestBody Formateur formateur){
        this.adminService.modifierFomateur(id, formateur);
    }
}
