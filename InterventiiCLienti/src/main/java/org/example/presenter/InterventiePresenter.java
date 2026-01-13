package org.example.presenter;

import org.example.mvp.entity.InterventieEntity;
import org.example.mvp.observer.Client;
import org.example.mvp.observer.Notificare;
import org.example.mvp.service.NotificationService;
import org.example.mvp.state.Interventie;
import org.example.repository.InterventieRepository;
import org.example.view.InterventieView;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Component
public class InterventiePresenter {

    private final InterventieRepository repository;
    private final NotificationService notificationService;

    // Map pentru a ține obiectele Interventie (cu pattern State)
    private final Map<Long, Interventie> interventiiMap;

    public InterventiePresenter(InterventieRepository repository,
                                NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
        this.interventiiMap = new HashMap<>();
    }

    public void afiseazaToateInterventiile(Model model) {
        List<InterventieEntity> interventii = repository.findAll();
        model.addAttribute("interventii", interventii);
    }

    public void modificaStare(Long id, String stareNoua, RedirectAttributes redirectAttributes) {
        if (stareNoua == null || stareNoua.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("eroare",
                    "Trebuie să selectați o stare nouă!");
            return;
        }

        Optional<InterventieEntity> optEntity = repository.findById(id);

        if (!optEntity.isPresent()) {
            redirectAttributes.addFlashAttribute("eroare",
                    "Intervenția #" + id + " nu a fost găsită!");
            return;
        }

        InterventieEntity entity = optEntity.get();
        String stareVeche = entity.getStare();
        String numeClient = entity.getClient();

        if (stareVeche.equalsIgnoreCase(stareNoua)) {
            redirectAttributes.addFlashAttribute("eroare",
                    "Starea selectată este aceeași cu starea actuală!");
            return;
        }

        // Actualizează starea
        entity.setStare(stareNoua);
        repository.save(entity);

        // 🔔 TRIMITE NOTIFICARE ÎN CONSOLĂ
        notificationService.notificaSchimbareStare(numeClient, id, stareVeche, stareNoua);

        redirectAttributes.addFlashAttribute("mesaj",
                "Interventie #" + id + " actualizată din '" + formatStare(stareVeche) +
                        "' în '" + formatStare(stareNoua) + "'. Clientul " + numeClient + " a fost notificat!");
    }


    public void cautaDupaClient(String numeClient, Model model) {
        List<InterventieEntity> interventii = repository.findByClientContainingIgnoreCase(numeClient);
        model.addAttribute("interventii", interventii);
        model.addAttribute("numeClient", numeClient);

    }

    public void filtreazaDupaStare(String stare, Model model) {
        List<InterventieEntity> interventii;
        if (stare == null || stare.isEmpty()) {
            interventii = repository.findAll();
        } else {
            interventii = repository.findByStare(stare);
        }
        model.addAttribute("interventii", interventii);
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
                return "Echipă plecată spre client";
            default:
                return stare;
        }
    }

}