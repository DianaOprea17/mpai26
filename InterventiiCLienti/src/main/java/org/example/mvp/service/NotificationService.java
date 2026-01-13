package org.example.mvp.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notificaSchimbareStare(String numeClient, Long interventieId, String stareVeche, String stareNoua) {
        String mesaj = formatMesajNotificare(stareNoua);

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔔 NOTIFICARE CLIENT");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("👤 Client: " + numeClient);
        System.out.println("🆔 Interventie ID: #" + interventieId);
        System.out.println("📊 Stare anterioară: " + formatStare(stareVeche));
        System.out.println("📊 Stare nouă: " + formatStare(stareNoua));
        System.out.println("💬 Mesaj: " + mesaj);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    private String formatMesajNotificare(String stare) {
        if (stare == null) {
            return "Starea intervenției a fost actualizată.";
        }

        switch (stare) {
            case "TRANSMISA":
                return "Solicitarea dvs. a fost transmisă cu succes!";
            case "ACCEPTATA":
                return "Solicitarea dvs. a fost acceptată. Echipa este disponibilă!";
            case "ECHIPA_PLECATA":
                return "Echipa a plecat spre locația dvs.!";
            default:
                return "Starea intervenției a fost actualizată.";
        }
    }

    private String formatStare(String stare) {
        if (stare == null) {
            return null;
        }

        switch (stare) {
            case "TRANSMISA":
                return "Solicitare transmisă";
            case "ACCEPTATA":
                return "Solicitare acceptată";
            case "ECHIPA_PLECATA":
                return "Echipă plecată";
            default:
                return stare;
        }
    }

}
