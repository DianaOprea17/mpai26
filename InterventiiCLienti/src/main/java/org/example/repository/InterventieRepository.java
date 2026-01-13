package org.example.repository;

import org.example.mvp.entity.InterventieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InterventieRepository extends JpaRepository<InterventieEntity, Long> {

    List<InterventieEntity> findByClient(String client);
    List<InterventieEntity> findByStare(String stare);
    List<InterventieEntity> findByDataInterventiei(LocalDate dataInterventiei);
    List<InterventieEntity> findByClientContainingIgnoreCase(String client);
}
