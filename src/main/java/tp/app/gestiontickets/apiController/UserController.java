package tp.app.gestiontickets.apiController;

import org.springframework.web.bind.annotation.*;
import tp.app.gestiontickets.model.UserEntity;
import tp.app.gestiontickets.services.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> voirUsers() {
        return this.userService.getUsers();
    }

    @PostMapping("/create")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return this.userService.creer(user);
    }
}
