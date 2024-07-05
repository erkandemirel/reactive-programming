package com.erkndmrl.reactive.repository;

import com.erkndmrl.reactive.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

}
