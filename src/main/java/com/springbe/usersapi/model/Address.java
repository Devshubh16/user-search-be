package com.springbe.usersapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Embeddabl
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private String address;
    private String city;
    private String state;
    private String stateCode;
    private String postalCode;
    private String country;

    @Embedded
    private Coordinates coordinates;
}

