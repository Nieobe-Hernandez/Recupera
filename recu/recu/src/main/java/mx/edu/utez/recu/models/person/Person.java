package mx.edu.utez.recu.models.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20,nullable = false)
    private String lastname;

    @Column(length = 20,nullable = false)
    private String direction;

    @Column(length = 20,nullable = false)
    private String contact;

    public Person(String name, String lastname, String direction, String contact) {
        this.name = name;
        this.lastname = lastname;
        this.direction = direction;
        this.contact = contact;
    }
}
