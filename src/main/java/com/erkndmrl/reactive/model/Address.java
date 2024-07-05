package com.erkndmrl.reactive.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Address")
@Data
public class Address {
    @Id
    private String id;
    private String personId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;

}
