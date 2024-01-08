package com.example.dentiste.repository;

import com.example.dentiste.model.Cout;
import com.example.dentiste.model.TypeDent;
import com.example.dentiste.model.TypeOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoutRepository extends JpaRepository<Cout,Long> {
    @Query("SELECT c FROM Cout c WHERE c.typeDent.id = :typeDentId")
    Cout getCoutByTypeDent(@Param("typeDentId") Long typeDentId);

    @Query("SELECT c FROM Cout c WHERE c.typeDent.id = :typeDentId AND c.typeOperation.id = :typeOperationId")
    Cout getCoutByTypeDentAndTypeOperation(
            @Param("typeDentId") Long typeDentId,
            @Param("typeOperationId") Long typeOperationId
    );

}
