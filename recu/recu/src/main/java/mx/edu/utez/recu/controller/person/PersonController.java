package mx.edu.utez.recu.controller.person;

import jakarta.validation.Valid;
import mx.edu.utez.recu.config.ApiResponse;
import mx.edu.utez.recu.controller.person.dto.PersonDto;
import mx.edu.utez.recu.models.person.Person;
import mx.edu.utez.recu.service.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return personService.getAll();
    }

    @PostMapping("/")
    public ResponseEntity<?> createPerson(@Valid @RequestBody PersonDto personDto){

        ResponseEntity<ApiResponse> savedPerson = personService.save(personDto.toEntity());

        return ResponseEntity.ok(savedPerson.getBody());

    }

    @PutMapping("/{id}")
    public ResponseEntity<?>updatePerson(@RequestBody PersonDto personDto){
        return personService.save(
                personDto.toEntity()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        return personService.delete(id);
    }

}
