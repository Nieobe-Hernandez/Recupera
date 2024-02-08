package mx.edu.utez.recu.controller.user;

import jakarta.validation.Valid;
import mx.edu.utez.recu.controller.user.dto.UserDto;
import mx.edu.utez.recu.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){
        return userService.save(
                userDto.toEntity(user)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updateUser(@RequestBody UserDto userDto){
        return userService.save(
                userDto.toEntity(user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userService.delete(id);
    }
}
