package com.safetynet.safetynetalerts.model;

import lombok.Data;
import lombok.Generated;

import java.util.List;
import java.util.Objects;

@Data
public class DataContainer {

    private List<Person> persons;

    private List<Firestation> firestations;

    private List<Medicalrecord> medicalrecords;

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataContainer that = (DataContainer) o;
        return Objects.equals(persons, that.persons) && Objects.equals(firestations, that.firestations) && Objects.equals(medicalrecords, that.medicalrecords);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(persons, firestations, medicalrecords);
    }
}
