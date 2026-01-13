package org.example.service;

import org.example.model.observer.Client;
import org.example.model.observer.Notificare;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    private final Notificare notificare;
    private final Map<String, Client> clientiInregistrati;

    public NotificationService() {
        this.notificare = new Notificare();
        this.clientiInregistrati = new HashMap<>();
    }

    // Înregistrează un client pentru notificări (dacă nu există deja)
    public void inregistreazaClient(String numeClient) {
        if (!clientiInregistrati.containsKey(numeClient)) {
            Client client = new Client(numeClient, "");
            clientiInregistrati.put(numeClient, client);
            notificare.addObserver(client);
        }
    }

    // Trimite notificare pentru modificarea stării comenzii
    public void notificaSchimbareStare(String numeClient, Long comandaId, String stareVeche, String stareNoua) {
        inregistreazaClient(numeClient);

        String mesaj = String.format(
                "Comanda #%d a fost actualizată din '%s' în '%s'",
                comandaId, stareVeche, stareNoua
        );

        // Setează mesajul și notifică observatorii
        notificare.setMessage(mesaj);
        notificare.notifyObservers();
    }
}