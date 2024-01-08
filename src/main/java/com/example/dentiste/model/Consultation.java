package com.example.dentiste.model;

import com.example.dentiste.repository.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_consultaion", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    Patient patient;

    double budget;

    int typeConsultaion;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getTypeConsultaion() {
        return typeConsultaion;
    }

    public void setTypeConsultaion(int typeConsultaion) {
        this.typeConsultaion = typeConsultaion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consultation(Long id, Patient patient, double budget, int typeConsultaion) {
        this.id = id;
        this.patient = patient;
        this.budget = budget;
        this.typeConsultaion = typeConsultaion;
    }

    public Consultation(Patient patient, double budget, int typeConsultaion) {
        this.patient = patient;
        this.budget = budget;
        this.typeConsultaion = typeConsultaion;
    }

    public Consultation(){

    }



    //////fonction///////////////

    public void reparerDentParPriorite(InfoDentRepository infoDentRepository, CoutRepository coutRepository, ConsultaionRepository consultaionRepository , TypeOperationRepository typeOperationRepository,EtatRepository etatRepository){
        List<InfoDent> lastInfoDents=infoDentRepository.getLastDateInfoDentByIdPatient(this.patient.getId());
        System.out.println("size="+lastInfoDents.size());
        // Trier les dents en fonction de la priorité définie dans Cout
        lastInfoDents.sort(Comparator.comparingInt(infoDent -> {

//            System.out.println("infodentetat: " +infoDent.getEtat().getId());
            // Pour chaque InfoDent, on récupère le Cout correspondant à la dent et à l'opération associée
            TypeOperation typeOperation = typeOperationRepository.getTypeOperationByIntervalle(infoDent.getEtat().getId());
            Cout cout = coutRepository.getCoutByTypeDentAndTypeOperation(
                    infoDent.getDent().getTypeDent().getId(),
                    typeOperation.getId()
            );

            // Si le Cout est trouvé, on utilise sa priorité pour le tri décroissant
            // Le tri décroissant est effectué en renvoyant le négatif de la priorité
            if (typeConsultaion == 1) {
                // Si le type de consultation est 1, trier par ordre décroissant
                return cout != null ? -cout.getPriorisation() : 0;
            } else if (typeConsultaion == 2) {
                // Si le type de consultation est 2, trier par ordre croissant
                return cout != null ? cout.getPriorisation() : 0;
            } else {
                // Autre logique de tri par défaut
                return 0;
            }
        }));
        // Initialiser le budget restant avec le budget de la consultation
        double budgetRestant = consultaionRepository.getBudgetByIdPatient(patient.getId(),this.getId());
        // Parcourir les dents et effectuer les réparations dans l'ordre de priorité
        for (InfoDent infoDent : lastInfoDents) {
            if (budgetRestant <= 0) {
                // Le budget est épuisé, arrêter les réparations
                break;
            }
            // Récupérer le type d'opération à effectuer en fonction de l'état de la dent
            TypeOperation typeOperation = typeOperationRepository.getTypeOperationByIntervalle(infoDent.getEtat().getId());

            if (typeOperation == null) {
                System.out.println("Type d'opération non trouvé pour l'état de la dent: " + infoDent.getDent().getId());
                continue;
            }

            // Récupérer le coût initial de la réparation
            Cout cout = coutRepository.getCoutByTypeDentAndTypeOperation(
                    infoDent.getDent().getTypeDent().getId(),
                    typeOperation.getId()
            );

            if (cout == null) {
                System.out.println("Cout non trouvé pour la dent: " + infoDent.getDent().getId());
                continue;
            }

            double coutReparationInitial = cout.getPrixOperation();

            double coutReparation = coutReparationInitial;

            System.out.println("etatinitial:"+Math.toIntExact(infoDent.getEtat().getId()));

            int etatActuel = Math.toIntExact(infoDent.getEtat().getId());
            while (etatActuel < 10 && budgetRestant >= coutReparation) {

                if (budgetRestant >= coutReparation) {
                    System.out.println("Budget:"+ budgetRestant);
                    System.out.println("Coutreparation:"+coutReparation);
                    System.out.println("operation effectuee: "+typeOperation.nomOperation);
                    // Si le budget permet la réparation, déduire le coût du budget restant
                    budgetRestant -= coutReparation;
                    System.out.println("reste: " +budgetRestant);


                    // Mettre à jour l'état de la dent
                    System.out.println("Etat actuel avant mise à jour : " + etatActuel);
                    if (typeOperation.getId() == 1) {
                        // Si le type d'opération est 1, mettre l'état à 10
                        etatActuel = 10;
                    }
                    else if (etatActuel >= 1 && etatActuel <= 3 && typeOperation.getId() == 2) {
                        // Si l'état actuel est entre 1 et 3 et que l'opération nécessite une id_operation deux,
                        // mettre l'état à 0
                        etatActuel = 0;
                    } else {
                        // Sinon, augmenter l'état de la dent
                        etatActuel++;
                    }

                    System.out.println("etatactuell: "+etatActuel);
                    // Insérer une nouvelle information sur la dent avec la date et le nouvel état
                    InfoDent nouvelleInfoDent = new InfoDent();
                    nouvelleInfoDent.setEtat(etatRepository.getById((long) etatActuel));
                    nouvelleInfoDent.setPatient(patient);
                    nouvelleInfoDent.setDent(infoDent.getDent());
                    nouvelleInfoDent.setDateInfoDent(LocalDateTime.now());
                    infoDentRepository.save(nouvelleInfoDent);
                    etatActuel = Math.toIntExact(nouvelleInfoDent.getEtat().getId());
                    typeOperation = typeOperationRepository.getTypeOperationByIntervalle(nouvelleInfoDent.getEtat().getId());

                    System.out.println("intervention avec succès: " + infoDent.getDent().getId());
                } else {
                    // Si le budget n'est pas suffisant pour la réparation, ignorer cette dent
                    System.out.println("Budget insuffisant pour réparer la dent: " + infoDent.getDent().getId());
                    break;  // Sortir de la boucle interne si le budget est insuffisant
                }


            }

        }

    }
}

