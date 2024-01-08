package com.example.dentiste.model;

import jakarta.persistence.*;

@Entity
public class Cout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_type_dent")
    TypeDent typeDent;

    @ManyToOne
    @JoinColumn(name = "id_type_operation")
    TypeOperation typeOperation;

    double prixOperation;
    int priorisation;

    public int getPriorisation() {
        return priorisation;
    }

    public Cout(Long id, TypeDent typeDent, TypeOperation typeOperation, double prixOperation, int priorisation) {
        this.id = id;
        this.typeDent = typeDent;
        this.typeOperation = typeOperation;
        this.prixOperation = prixOperation;
        this.priorisation = priorisation;
    }

    public Cout(TypeDent typeDent, TypeOperation typeOperation, double prixOperation, int priorisation) {
        this.typeDent = typeDent;
        this.typeOperation = typeOperation;
        this.prixOperation = prixOperation;
        this.priorisation = priorisation;
    }

    public void setPriorisation(int priorisation) {
        this.priorisation = priorisation;
    }

    public TypeDent getTypeDent() {
        return typeDent;
    }

    public void setTypeDent(TypeDent typeDent) {
        this.typeDent = typeDent;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public double getPrixOperation() {
        return prixOperation;
    }

    public void setPrixOperation(double prixOperation) {
        this.prixOperation = prixOperation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cout(Long id, TypeDent typeDent, TypeOperation typeOperation, double prixOperation) {
        this.id = id;
        this.typeDent = typeDent;
        this.typeOperation = typeOperation;
        this.prixOperation = prixOperation;
    }

    public Cout(TypeDent typeDent, TypeOperation typeOperation, double prixOperation) {
        this.typeDent = typeDent;
        this.typeOperation = typeOperation;
        this.prixOperation = prixOperation;
    }
    public Cout(){

    }
}
