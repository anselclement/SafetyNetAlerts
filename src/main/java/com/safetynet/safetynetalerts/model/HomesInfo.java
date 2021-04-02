package com.safetynet.safetynetalerts.model;

import lombok.Data;
import lombok.Generated;

import java.util.Objects;

@Data
public class HomesInfo {

    private String lastName;

    private String firstName;

    private String address;

    private String phone;

    private String age;

    private String medications;

    private String allergies;

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomesInfo homesInfo = (HomesInfo) o;
        return Objects.equals(lastName, homesInfo.lastName) && Objects.equals(address, homesInfo.address) && Objects.equals(phone, homesInfo.phone) && Objects.equals(age, homesInfo.age) && Objects.equals(medications, homesInfo.medications) && Objects.equals(allergies, homesInfo.allergies);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(lastName, address, phone, age, medications, allergies);
    }

    @Override
    @Generated
    public String toString() {
        return "HomesInfo{" +
                "lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
    }
}
