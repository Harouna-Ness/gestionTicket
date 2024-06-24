package tp.app.gestiontickets.services;

import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Role;
import tp.app.gestiontickets.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role creerRole(Role role) {
        return this.roleRepository.save(role);
    }

    public Optional<Role> recupererRolebyId(long id) {
        return this.roleRepository.findById(id);
    }

    public Role recupererRoleByLibelle(String libelle){
        return this.roleRepository.findRoleByLibelle(libelle);
    }

    public List<Role> recupererRoles() {
        return this.roleRepository.findAll();
    }

    public void supprimerRoleById(long id) {
        this.roleRepository.deleteById(id);
    }
}
