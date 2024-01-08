package com.example.dentiste.model;

import jakarta.persistence.*;

@Entity
public class TypeDent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type_dent", nullable = false)
    private Long id;
    private int numeroDent;
    private String nomDent;

    public int getNumeroDent() {
        return numeroDent;
    }

    public void setNumeroDent(int numeroDent) {
        this.numeroDent = numeroDent;
    }

    public String getNomDent() {
        return nomDent;
    }

    public void setNomDent(String nomDent) {
        this.nomDent = nomDent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDent(Long id, int numeroDent, String nomDent) {
        this.id = id;
        this.numeroDent = numeroDent;
        this.nomDent = nomDent;
    }

    public TypeDent(int numeroDent, String nomDent) {
        this.numeroDent = numeroDent;
        this.nomDent = nomDent;
    }
    public TypeDent(){}
}
