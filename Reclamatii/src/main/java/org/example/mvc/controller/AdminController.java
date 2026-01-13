package org.example.mvc.controller;

import org.example.mvc.entity.ReclamatieEntity;
import org.example.mvc.repository.ReclamatieRepository;
import org.example.mvc.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    private final ReclamatieRepository reclamatieRepository;
    private final NotificationService notificationService;

    public AdminController(ReclamatieRepository reclamatieRepository,
                           NotificationService notificationService) {
        this.reclamatieRepository = reclamatieRepository;
        this.notificationService = notificationService;
    }
    @GetMapping("/admin")
    public String navigateToAdminsPage(Model model) {
        List<ReclamatieEntity> reclamatii = reclamatieRepository.findAll();
        model.addAttribute("reclamatii", reclamatii);
        return "admin/index";
    }

    //modifica starea comenzii
    @PostMapping("/admin/modifica-stare/{id}")
    public String modificaStare(@PathVariable("id") Long id,
                                @RequestParam("stareNoua") String stareNoua,
                                RedirectAttributes redirectAttributes) {

        if (stareNoua == null || stareNoua.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    "Trebuie să selectați o stare nouă!");
            return "redirect:/admin";
        }

        Optional<ReclamatieEntity> comandaOpt = reclamatieRepository.findById(id);

        if (comandaOpt.isPresent()) {
            ReclamatieEntity reclamatie = comandaOpt.get();
            String stareVeche = reclamatie.getStare();
            String numeClient = reclamatie.getClient();

            if (stareVeche.equalsIgnoreCase(stareNoua)) {
                redirectAttributes.addFlashAttribute("error",
                        "Starea selectată este aceeași cu starea actuală!");
                return "redirect:/admin";
            }

            // Actualizează starea
            reclamatie.setStare(stareNoua);
            reclamatieRepository.save(reclamatie);

            // 🔔 TRIMITE NOTIFICARE CLIENTULUI
            notificationService.notificaSchimbareStare(numeClient, id, stareVeche, stareNoua);

            redirectAttributes.addFlashAttribute("success",
                    "Reclamatia #" + id + " a fost actualizată din '" + stareVeche + "' în '" + stareNoua + "'. Clientul " + numeClient + " a fost notificat!");
        } else {
            redirectAttributes.addFlashAttribute("error",
                    "Reclamatia #" + id + " nu a fost găsită!");
        }

        return "redirect:/admin";
    }
}
