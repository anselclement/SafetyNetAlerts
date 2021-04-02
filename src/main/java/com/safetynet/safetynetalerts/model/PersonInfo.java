package com.safetynet.safetynetalerts.model;

import lombok.Data;
import lombok.Generated;

import java.util.Objects;

@Data
public class PersonInfo {

    private String lastName;

    private String firstName;

    private String phone;

    private String age;

    private String station;

    private String medications;

    private String allergies;

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonInfo that = (PersonInfo) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(phone, that.phone) && Objects.equals(age, that.age) && Objects.equals(station, that.station) && Objects.equals(medications, that.medications) && Objects.equals(allergies, that.allergies);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(lastName, firstName, phone, age, station, medications, allergies);
    }

    @Override
    @Generated
    public String toString() {
        return "PersonInfo{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                ", station='" + station + '\'' +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
