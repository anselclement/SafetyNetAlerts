package com.safetynet.safetynetalerts.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "medicalrecords")
public class Medicalrecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String birthdate;


    //TODO : delete cascade
    @ElementCollection
    private List<String> medications;

    //TODO : delete cascade
    @ElementCollection
    private List<String> allergies;

    public Medicalrecord(){}

    public Medicalrecord(Long id, String firstName, String lastName, String birthdate, List<String> medications, List<String> allergies){}

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicalrecord that = (Medicalrecord) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthdate, that.birthdate) && Objects.equals(medications, that.medications) && Objects.equals(allergies, that.allergies);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthdate, medications, allergies);
    }
}
