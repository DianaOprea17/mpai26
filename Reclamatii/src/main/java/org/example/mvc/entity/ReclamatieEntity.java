package org.example.mvc.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="reclamatii")
public class ReclamatieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String client;
    private String stare;
    @Column(name = "data_reclamatiei")
    private LocalDate dataReclamatiei;

    public ReclamatieEntity(String client, String stare, LocalDate dataReclamatiei) {
        this.client = client;
        this.stare = stare;
        this.dataReclamatiei = dataReclamatiei;
    }

    public ReclamatieEntity() {
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

    public LocalDate getDataReclamatiei() {
        return dataReclamatiei;
    }

    public void setDataReclamatiei(LocalDate dataReclamatiei) {
        this.dataReclamatiei = dataReclamatiei;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
