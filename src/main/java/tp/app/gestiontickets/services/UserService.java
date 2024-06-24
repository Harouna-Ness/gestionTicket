package tp.app.gestiontickets.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tp.app.gestiontickets.model.UserEntity;
import tp.app.gestiontickets.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity creer(UserEntity userEntity) {
        userEntity.setMdp(this.passwordEncoder().encode(userEntity.getMdp()));
        return this.userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }
}
