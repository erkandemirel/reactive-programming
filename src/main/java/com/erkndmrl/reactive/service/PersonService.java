package com.erkndmrl.reactive.service;

import com.erkndmrl.reactive.dto.PersonDTO;
import com.erkndmrl.reactive.exceptions.NotFoundException;
import com.erkndmrl.reactive.model.Address;
import com.erkndmrl.reactive.model.Person;
import com.erkndmrl.reactive.repository.AddressRepository;
import com.erkndmrl.reactive.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    public Mono<Person> getPersonById(String id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Person not found with ID: " + id)));
    }

    public Flux<Person> getPersons() {
        return personRepository.findAll();
    }

    public Mono<Person> savePerson(Person personDTO) {
        return personRepository.save(personDTO);
    }

    public Mono<Person> updatePerson(String id, Person personDTO) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Person not found with ID: " + id)))
                .flatMap(person -> {
                    person.setName(personDTO.getName());
                    person.setAge(personDTO.getAge());
                    return personRepository.save(person);
                });
    }

    public Mono<Void> deletePerson(String id) {

        return personRepository.deleteById(id);
    }

    public Mono<PersonDTO> getPersonWithAddresses(String personId) {
        Mono<Person> personMono = personRepository.findById(personId);
        Flux<Address> addressFlux = addressRepository.findByPersonId(personId);

        return Mono.zip(personMono, addressFlux.collectList(), persontDTOBiFunction);
    }

    private BiFunction<Person, List<Address>, PersonDTO> persontDTOBiFunction = (person, addressList) -> PersonDTO.builder()
            .age(person.getAge())
            .name(person.getName())
            .addresses(addressList)
            .build();
}
