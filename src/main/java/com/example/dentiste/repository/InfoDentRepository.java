package com.example.dentiste.repository;

import com.example.dentiste.model.InfoDent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoDentRepository extends JpaRepository<InfoDent,Long> {
    @Query("select i from InfoDent i where i.patient.id = :patient" + " and i.dateInfoDent = (select max (i2.dateInfoDent) from InfoDent i2 where i2.dent.id=i.dent.id)")
    List<InfoDent> getLastDateInfoDentByIdPatient(@Param("patient") Long patient);

}
