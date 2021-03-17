package com.safetynet.safetynetalerts.model;


import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;

    public Person() {}

    public Person(long l, String firstName, String lastName, String address, String city, String zip, String phone, String email) {
    }

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(city, person.city) && Objects.equals(zip, person.zip) && Objects.equals(phone, person.phone) && Objects.equals(email, person.email);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, city, zip, phone, email);
    }
}
