package mx.edu.utez.recu.service.person;

import mx.edu.utez.recu.config.ApiResponse;
import mx.edu.utez.recu.controller.person.dto.PersonDto;
import mx.edu.utez.recu.models.person.Person;
import mx.edu.utez.recu.models.person.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseEntity<ApiResponse> save(Person person){
        person = repository.saveAndFlush(person);
        return new ResponseEntity<>(new ApiResponse(person, HttpStatus.OK), HttpStatus.OK);
    }


    // Service

    public ResponseEntity<?> update(Long id, PersonDto personDto){

        Optional<Person> person = repository.findById(id);

        if(!person.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Person updatedPerson = personDto.toEntity();

        updatedPerson.setId(id);

        updatedPerson = repository.save(updatedPerson);

        return ResponseEntity.ok(updatedPerson);

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<Person> foundPerson = repository.findById(id);
        if (foundPerson.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "PersonNotFound"), HttpStatus.NOT_FOUND);
        }

        Person personDelete = foundPerson.get();


        repository.delete(personDelete);

        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false, "PersonDeletedSuccessfully"), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> getAll(){
        return  new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK),HttpStatus.OK);
    }
}
