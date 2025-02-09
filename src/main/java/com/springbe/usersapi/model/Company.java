package com.springbe.usersapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String department;
    private String name;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_address_id", referencedColumnName = "id")
    private Address address;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}


