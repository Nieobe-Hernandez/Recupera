package mx.edu.utez.recu.controller.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.recu.models.user.User;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Boolean status;

    public User toEntity(User user){
        return new User(username,password,status);
    }

}
