package com.erkndmrl.reactive.repository;

import com.erkndmrl.reactive.model.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AddressRepository extends ReactiveMongoRepository<Address, String> {
    Flux<Address> findByPersonId(String personId);
}
