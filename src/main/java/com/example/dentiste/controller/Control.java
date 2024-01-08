package com.example.dentiste.controller;

import com.example.dentiste.model.*;
import com.example.dentiste.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class Control {
    @Autowired
    ConsultaionRepository consultationRepository;
    @Autowired
    CoutRepository coutRepository;
    @Autowired
    DentRepository dentRepository;
    @Autowired
    EtatRepository etatRepository;
    @Autowired
    InfoDentRepository infoDentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    TypeOperationRepository typeOperationRepository;

    @GetMapping("/insertPatient")
    public String home(Model model){
        model.addAttribute("listEtats", etatRepository.findAll());
        return "InsertPatient";
    }

    @PostMapping("/patientpost")
    public RedirectView newPatient(@ModelAttribute Patient patient, @RequestParam Map<String, String> params) {
        // Enregistrez le patient
        patientRepository.save(patient);

        // Obtenez les informations sur les dents du formulaire
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();

            if (paramName.startsWith("etatDent")) {
                // Le paramètre est lié à une dent, traitez-le comme vous le souhaitez
                int dentNumber = Integer.parseInt(paramName.substring(8)); // Extrait le numéro de la dent
                Long etatId = Long.parseLong(paramValue); // Récupère l'ID de l'état

                // Créez et enregistrez l'objet InfoDent
                InfoDent infoDent = new InfoDent();
                infoDent.setPatient(patient);
                // Récupérez la dent en fonction du numéro de dent
                Dent dent = dentRepository.getDentByNumeroDent(dentNumber);
                infoDent.setDent(dent);
                // Récupérez l'état en fonction de l'ID de l'état
                Etat etat = etatRepository.getEtatById(etatId);
                infoDent.setEtat(etat);
                infoDent.setDateInfoDent(LocalDateTime.now());

                // Enregistrez l'infoDent
                infoDentRepository.save(infoDent);
            }
        }

        final RedirectView redirectView = new RedirectView("/insertPatient", true);
        return redirectView;
    }


    ///////////////consultation/////////////////

    @GetMapping("/insertConsultation")
    public String consult(Model model){
        model.addAttribute("listPatients", patientRepository.findAll());
        return "InsertConsultation";
    }

    @PostMapping("/consultationpost")
    public RedirectView consultationPost(
            @RequestParam("patient") Long patientId,
            @RequestParam("budget") Integer budget,
            @RequestParam("type_consultation") Integer typeConsultation,
            RedirectAttributes attributes
    ) {

        // Récupérer le patient
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();

            // Enregistrer la consultation
            Consultation consultation = new Consultation();
            consultation.setPatient(patient);
            consultation.setBudget(budget);
            consultation.setTypeConsultaion(typeConsultation);

            consultationRepository.save(consultation);

            attributes.addAttribute("consultation", consultation);

            // Réparer les dents par priorité

            consultation.reparerDentParPriorite(infoDentRepository, coutRepository, consultationRepository, typeOperationRepository, etatRepository);


            // Redirection avec un message de succès
            attributes.addFlashAttribute("message", "Consultation enregistrée avec succès!");

        } else {
            // Gérer le cas où le patient n'est pas trouvé
            attributes.addFlashAttribute("message", "Erreur: Patient non trouvé");
        }

        // Redirection vers une autre page
        return new RedirectView("/page-de-redirection", true);
    }


    /////LISTE///
    @GetMapping("/listeconsultation")
    public String listeconsult(Model model){
        model.addAttribute("listConsultations", consultationRepository.findAll());
        return "consultationList";
    }

    @GetMapping("/consultationDetails")
    public String consultationDetails(Model model, Long consultationId) {
        Consultation consultation = consultationRepository.findById(consultationId).orElse(null);
        if (consultation != null) {
            List<InfoDent> listeDents = infoDentRepository.getLastDateInfoDentByIdPatient(consultation.getPatient().getId());
            model.addAttribute("consultation", consultation);
            model.addAttribute("listeDents", listeDents);
            return "consultationDetails";
        } else {
            return "redirect:/consultations/list";
        }
    }

}
