package com.example.dentiste.model;

import com.example.dentiste.repository.InfoDentRepository;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class InfoDent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_infoDent", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_etat")
    private Etat etat;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_dent")
    Dent dent;

    LocalDateTime dateInfoDent;

    public LocalDateTime getDateInfoDent() {
        return dateInfoDent;
    }

    public void setDateInfoDent(LocalDateTime dateInfoDent) {
        this.dateInfoDent = dateInfoDent;
    }

    public Dent getDent() {
        return dent;
    }

    public InfoDent(Long id, Etat etat, Patient patient, Dent dent) {
        this.id = id;
        this.etat = etat;
        this.patient = patient;
        this.dent = dent;
    }

    public InfoDent(Etat etat, Patient patient, Dent dent) {
        this.etat = etat;
        this.patient = patient;
        this.dent = dent;
    }

    public void setDent(Dent dent) {
        this.dent = dent;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InfoDent(Long id, Etat etat, Patient patient) {
        this.id = id;
        this.etat = etat;
        this.patient = patient;
    }

    public InfoDent(Etat etat, Patient patient) {
        this.etat = etat;
        this.patient = patient;
    }
    public InfoDent (){

    }

    public InfoDent(Long id, Etat etat, Patient patient, Dent dent, LocalDateTime dateInfoDent) {
        this.id = id;
        this.etat = etat;
        this.patient = patient;
        this.dent = dent;
        this.dateInfoDent = dateInfoDent;
    }

    public InfoDent(Etat etat, Patient patient, Dent dent, LocalDateTime dateInfoDent) {
        this.etat = etat;
        this.patient = patient;
        this.dent = dent;
        this.dateInfoDent = dateInfoDent;
    }


    public List<InfoDent> getAlldentByPatientByLastDate(InfoDentRepository infoDentRepository,Long idPatient){
         return infoDentRepository.getLastDateInfoDentByIdPatient(idPatient);
    }
}
