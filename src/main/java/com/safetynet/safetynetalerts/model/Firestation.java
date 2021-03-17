package com.safetynet.safetynetalerts.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "firestations")
public class Firestation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String station;

    public Firestation(){}

    public Firestation(Long id, String address, String station){ }

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firestation that = (Firestation) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(station, that.station);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, address, station);
    }
}
