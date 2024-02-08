package mx.edu.utez.recu.models.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String username;

    @Column(length = 4,nullable = false)
    private String password;

    @Column(length = 8, nullable = false)
    private Boolean status;

    public User(String username, String password, Boolean status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }
}
