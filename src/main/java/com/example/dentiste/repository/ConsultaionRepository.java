package com.example.dentiste.repository;

import com.example.dentiste.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaionRepository extends JpaRepository<Consultation,Long> {
    @Query("SELECT c.budget FROM Consultation c WHERE c.patient.id = :patientId AND c.id = :consultationId")
    Double getBudgetByIdPatient(@Param("patientId") Long patientId, @Param("consultationId") Long consultationId);

}
