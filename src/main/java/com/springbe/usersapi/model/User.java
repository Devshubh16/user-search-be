package com.springbe.usersapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data  // Lombok annotation for getters, setters, equals, hashCode, toString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private double height;
    private double weight;
    private String eyeColor;
    private String ip;
    private String macAddress;
    private String university;
    private String ein;
    private String ssn;
    private String userAgent;
    private String role;

    @Embedded
    private Hair hair;

    @ManyToOne(cascade = CascadeType.ALL) // Fix duplication by using @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id") 
    private Address address;

    @Embedded
    private Bank bank;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @Embedded
    private Crypto crypto;
    
    private String companyName; 
    
    public void setCompany(Company company) {
        this.companyName = (company != null) ? company.getName() : null;
    }


    public void setId(Long id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    
}
