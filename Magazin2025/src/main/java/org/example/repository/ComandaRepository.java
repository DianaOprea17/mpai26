package org.example.repository;

import org.example.entity.ComandaEntity;
import org.example.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaEntity, Long> {
    List<ComandaEntity> findByClient(String client);
    List<ComandaEntity> findByStare(String stare);
    List<ComandaEntity> findByDataComenzii(LocalDate dataComenzii);

}
