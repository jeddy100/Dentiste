package com.example.dentiste.main;

import com.example.dentiste.model.Consultation;
import com.example.dentiste.model.InfoDent;
import com.example.dentiste.model.Patient;
import com.example.dentiste.model.TypeOperation;
import com.example.dentiste.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

    @Configuration
    public class Main {
        @Bean
        CommandLineRunner commandLineRunner(TypeOperationRepository typeOperationRepository,PatientRepository patientRepository,InfoDentRepository infoDentRepository, ConsultaionRepository consultaionRepository, CoutRepository coutRepository, DentRepository dentRepository, EtatRepository etatRepository){
            return args -> {
                List<InfoDent> linfo=infoDentRepository.findAll();
                InfoDent infoDent=linfo.get(0);
                Patient patient=patientRepository.findAll().get(0);
                List<InfoDent> listeDents=infoDent.getAlldentByPatientByLastDate(infoDentRepository, patient.getId());
//                for (int i = 0; i <listeDents.size() ; i++) {
//                    System.out.println("dent "+i+": "+listeDents.get(i).getDent().getTypeDent().getNumeroDent()+" type: "+listeDents.get(i).getDent().getTypeDent().getNomDent());
//
//                }

                Consultation consultation=consultaionRepository.findAll().get(0);
                System.out.println("consultation:"+consultation.getId()+" "+consultation.getPatient().getNom());

//                TypeOperation typeOperation=typeOperationRepository.getTypeOperationByIntervalle(8L);
//                System.out.println(typeOperation.getId()+" " + typeOperation.getNomOperation());


//                consultation.reparerDentParPriorite(infoDentRepository,coutRepository,consultaionRepository,typeOperationRepository,etatRepository);

            };

        }
    }


