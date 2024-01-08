package com.example.dentiste.repository;

import com.example.dentiste.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatRepository extends JpaRepository<Etat,Long> {
    @Query("SELECT e FROM Etat e WHERE e.id = :id")
    Etat getEtatById(@Param("id") Long id);
}
