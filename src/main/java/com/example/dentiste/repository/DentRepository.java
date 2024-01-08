package com.example.dentiste.repository;

import com.example.dentiste.model.Dent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DentRepository extends JpaRepository<Dent,Long> {
    @Query("SELECT d FROM Dent d WHERE d.typeDent.numeroDent = :numeroDent")
    Dent getDentByNumeroDent(@Param("numeroDent") int numeroDent);
}
