package org.example.mvc.repository;

import org.example.mvc.entity.ReclamatieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReclamatieRepository extends JpaRepository<ReclamatieEntity, Long> {

    List<ReclamatieEntity> findByClient(String client);
    List<ReclamatieEntity> findByStare(String stare);
    List<ReclamatieEntity> findByDataReclamatiei(LocalDate dataReclamatiei);
}
