package com.example.dentiste.model;

import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_patient", nullable = false)
    private Long id;
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Patient(String nom) {
        this.nom = nom;
    }
    public Patient(){

    }

}
