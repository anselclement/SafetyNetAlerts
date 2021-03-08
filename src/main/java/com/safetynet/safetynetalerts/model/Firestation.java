package com.safetynet.safetynetalerts.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "firestations")
public class Firestation{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private String station;
}
