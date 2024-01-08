package com.example.dentiste.model;

import jakarta.persistence.*;

@Entity
public class Etat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_etat", nullable = false)
    private Long id;
    String nomEtat;

    public String getNomEtat() {
        return nomEtat;
    }

    public void setNomEtat(String nomEtat) {
        this.nomEtat = nomEtat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat(Long id, String nomEtat) {
        this.id = id;
        this.nomEtat = nomEtat;
    }

    public Etat(String nomEtat) {
        this.nomEtat = nomEtat;
    }
    public Etat(){

    }
}
