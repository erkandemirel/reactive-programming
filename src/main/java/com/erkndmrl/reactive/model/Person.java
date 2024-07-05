package com.erkndmrl.reactive.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Person")
@Data
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;

}