package mx.edu.utez.recu.controller.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.recu.models.person.Person;

@Setter
@Getter
@NoArgsConstructor
public class PersonDto {
    private Long id;
    private String name;
    private String lastname;
    private String direction;
    private String contact;

    public Person toEntity(){
        return new Person(name,lastname,direction,contact);
    }
}
