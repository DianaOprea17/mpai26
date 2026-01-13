package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="comenzi")
public class ComandaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;
    private String stare;
    @Column(name = "data_comenzii")
    private LocalDate dataComenzii;

    public ComandaEntity(String client, String stare, LocalDate dataComenzii) {
        this.client = client;
        this.stare = stare;
        this.dataComenzii = dataComenzii;
    }

    public LocalDate getDataComenzii() {
        return dataComenzii;
    }

    public void setDataComenzii(LocalDate dataComenzii) {
        this.dataComenzii = dataComenzii;
    }

    public ComandaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }
}

