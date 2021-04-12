package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Date;
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

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthdate;

    private int age;

    @ElementCollection
    @CollectionTable(name = "medicalrecord_medications", joinColumns = @JoinColumn(name = "medicalrecord_id"))
    private List<String> medications;


    @ElementCollection
    @CollectionTable(name = "medicalrecord_allergies", joinColumns = @JoinColumn(name = "medicalrecord_id"))
    private List<String> allergies;

    public Medicalrecord(){}

    public Medicalrecord(Long id, String firstName, String lastName, Date birthdate, int age, List<String> medications, List<String> allergies){}

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

    @Override
    @Generated
    public String toString() {
        return "Medicalrecord{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", age=" + age +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
