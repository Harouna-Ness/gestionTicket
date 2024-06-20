package tp.app.gestiontickets.apiController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tp.app.gestiontickets.model.Admin;
import tp.app.gestiontickets.model.Apprenant;
import tp.app.gestiontickets.model.AuthentificationDTO;
import tp.app.gestiontickets.services.AdminService;
import tp.app.gestiontickets.services.ApprenantService;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AuthenticationManager authenticationManager;
    private AdminService adminService;

    public AdminController(AdminService adminService, ApprenantService apprenantService, AuthenticationManager authenticationManager) {
        this.adminService = adminService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public void creerAdmin() {
        this.adminService.createAdmin();
    }

    @PostMapping("/creerApprenant")
    public Apprenant creerApprenant(@RequestBody Apprenant apprenant) {
        return this.adminService.creerApprenant(apprenant);
    }
    @PostMapping("/supprimerApprenant/{id}")
    public void supprimerApprenant(@PathVariable long id) {
        this.adminService.supprimerApprenant(id);
    }

    @GetMapping("/connexion")
    public String connexion(@RequestBody AuthentificationDTO authentificationDTO) {

        return "new ResponseEntity<>(Authentication passed, HttpStatus.OK);";
    }
}
