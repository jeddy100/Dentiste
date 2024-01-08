package com.example.dentiste.repository;

import com.example.dentiste.model.TypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOperationRepository extends JpaRepository<TypeOperation,Long> {
    @Query("SELECT t FROM TypeOperation t WHERE :intervalle BETWEEN t.debutIntervalle AND t.finIntervalle")
    TypeOperation getTypeOperationByIntervalle(@Param("intervalle") Long intervalle);
}
