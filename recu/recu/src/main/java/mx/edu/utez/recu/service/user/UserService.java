package mx.edu.utez.recu.service.user;

import mx.edu.utez.recu.config.ApiResponse;
import mx.edu.utez.recu.controller.user.dto.UserDto;
import mx.edu.utez.recu.models.user.User;
import mx.edu.utez.recu.models.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public ResponseEntity<ApiResponse> save(User user){
        int n = (int)(Math.random()*26+1);
        int m = (int)(Math.random()*10*1);
        String [] abecedario = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
                "K", "L", "M","N","O","P","Q","R","S","T","U","V","W", "X","Y","Z" };
        int [] numero ={0, 1, 2, 3, 4, 5, 6 ,7, 8, 9};
        String aleatorio1= abecedario[n]+n;
        int aleatorio2=numero[m]+m;
        String password= (aleatorio1+aleatorio2);
        user.setPassword(password);
        user = repository.saveAndFlush(user);
        return new ResponseEntity<>(new ApiResponse(user, HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    // Service
    public ResponseEntity<?> update(Long id, UserDto userDto){
        Optional<User> optionalUser = repository.findById(id);
        if(!optionalUser.isPresent()){
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();

        String newPassword = generateRandomPassword();

        User updatedUser = userDto.toEntity(user);

        updatedUser.setPassword(newPassword);

        User savedUser = repository.save(updatedUser);

        return ResponseEntity.ok(savedUser);

    }

    public String generateRandomPassword(){
        String password = "";

        for(int i=0; i<8; i++) {

            // Código de generación de password aleatorio

        }

        return password;
    }

    //Eliminar
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<User> foundUser = repository.findById(id);
        if (foundUser.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "UserNotFound"), HttpStatus.NOT_FOUND);
        }

        User userDelete = foundUser.get();


        repository.delete(userDelete);

        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "UserDeletedSuccessfully"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return  new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK),HttpStatus.OK);
    }
}
