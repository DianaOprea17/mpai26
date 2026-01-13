package org.example.mvp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="interventii")
public class InterventieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client;
    private String stare;
    private String descriere;
    private String locatie;

    @Column(name = "data_interventiei")
    private LocalDate dataInterventiei;

    public InterventieEntity() {}

    public InterventieEntity(String client, String descriere, String locatie, String stare, LocalDate dataInterventiei) {
        this.client = client;
        this.descriere = descriere;
        this.locatie = locatie;
        this.stare = stare;
        this.dataInterventiei = dataInterventiei;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClient() { return client; }
    public void setClient(String client) { this.client = client; }

    public String getStare() { return stare; }
    public void setStare(String stare) { this.stare = stare; }

    public LocalDate getDataInterventiei() { return dataInterventiei; }
    public void setDataInterventiei(LocalDate dataInterventiei) { this.dataInterventiei = dataInterventiei; }
}
