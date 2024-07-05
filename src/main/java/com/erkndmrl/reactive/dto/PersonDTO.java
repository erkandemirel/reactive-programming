package com.erkndmrl.reactive.dto;

import com.erkndmrl.reactive.model.Address;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Builder
public class PersonDTO {
    private String name;
    private Integer age;
    private List<Address> addresses;
}
