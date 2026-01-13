package org.example.mvp.state;

import org.example.mvp.observer.Notificare;

import java.time.LocalDate;

public class Interventie {

    private State stare;
    private Notificare notificare;
    private String client;
    private String descriere;
    private String locatie;
    private LocalDate dataCrearii;

    public Interventie(String client, String descriere, String locatie) {
        this.stare = new SolicitareTransmisa();
        this.notificare = new Notificare();
        this.client = client;
        this.descriere = descriere;
        this.locatie = locatie;
        this.dataCrearii = LocalDate.now();
    }

    // Constructor pentru încărcare din entity
    public Interventie(String client, String descriere, String locatie, String stare, LocalDate data) {
        this.notificare = new Notificare();
        this.client = client;
        this.descriere = descriere;
        this.locatie = locatie;
        this.dataCrearii = data;

        // Setează starea corectă bazat pe string
        if (stare == null) {
            this.stare = new SolicitareTransmisa();
        } else {
            switch (stare) {
                case "ACCEPTATA":
                    this.stare = new SolicitareAcceptata();
                    break;
                case "ECHIPA_PLECATA":
                    this.stare = new EchipaPlecataSpreClient();
                    break;
                default:
                    this.stare = new SolicitareTransmisa();
                    break;
            }
        }

    }

    public void setStare(State stareNoua, String mesajClient) {
        this.stare = stareNoua;
        notificare.setMessage(mesajClient);
        notificare.notifyObservers();
    }

    public State getStare() {
        return stare;
    }

    public String getStareString() {
        if (stare instanceof SolicitareTransmisa) return "TRANSMISA";
        if (stare instanceof SolicitareAcceptata) return "ACCEPTATA";
        if (stare instanceof EchipaPlecataSpreClient) return "ECHIPA_PLECATA";
        return "NECUNOSCUTA";
    }

    public Notificare getNotificare() {
        return notificare;
    }

    public String getClient() {
        return client;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getLocatie() {
        return locatie;
    }

    public LocalDate getDataCrearii() {
        return dataCrearii;
    }
}