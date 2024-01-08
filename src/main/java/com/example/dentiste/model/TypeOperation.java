package com.example.dentiste.model;

import jakarta.persistence.*;

@Entity
public class TypeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_type_operation", nullable = false)
    private Long id;
    String nomOperation;
    int debutIntervalle;
    int finIntervalle;

    public int getDebutIntervalle() {
        return debutIntervalle;
    }

    public void setDebutIntervalle(int debutIntervalle) {
        this.debutIntervalle = debutIntervalle;
    }

    public int getFinIntervalle() {
        return finIntervalle;
    }

    public void setFinIntervalle(int finIntervalle) {
        this.finIntervalle = finIntervalle;
    }

    public String getNomOperation() {
        return nomOperation;
    }

    public void setNomOperation(String nomOperation) {
        this.nomOperation = nomOperation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeOperation(Long id, String nomOperation) {
        this.id = id;
        this.nomOperation = nomOperation;
    }

    public TypeOperation(String nomOperation) {
        this.nomOperation = nomOperation;
    }
    public TypeOperation(){

    }

    public TypeOperation(Long id, String nomOperation, int debutIntervalle, int finIntervalle) {
        this.id = id;
        this.nomOperation = nomOperation;
        this.debutIntervalle = debutIntervalle;
        this.finIntervalle = finIntervalle;
    }

    public TypeOperation(String nomOperation, int debutIntervalle, int finIntervalle) {
        this.nomOperation = nomOperation;
        this.debutIntervalle = debutIntervalle;
        this.finIntervalle = finIntervalle;
    }
}
