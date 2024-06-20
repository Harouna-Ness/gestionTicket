package tp.app.gestiontickets.services;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.Role;
import tp.app.gestiontickets.model.UserEntity;
import tp.app.gestiontickets.repositories.RoleRepository;
import tp.app.gestiontickets.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    public UserEntity creer(UserEntity user) {
        Role role = new Role();
        role.setLibelle("USER");
        Role userRole = roleRepository.save(role);
        user.setRole(userRole);
        return this.userRepository.save(user);
    }

    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }
}
