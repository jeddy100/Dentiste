package com.example.dentiste.model;

import jakarta.persistence.*;

@Entity
public class Dent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_dent", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_type_dent")
    TypeDent typeDent;

    public TypeDent getTypeDent() {
        return typeDent;
    }

    public void setTypeDent(TypeDent typeDent) {
        this.typeDent = typeDent;
    }

    public Dent(Long id, TypeDent typeDent) {
        this.id = id;
        this.typeDent = typeDent;
    }

    public Dent(TypeDent typeDent) {
        this.typeDent = typeDent;
    }

    public Dent(){

    }
}
