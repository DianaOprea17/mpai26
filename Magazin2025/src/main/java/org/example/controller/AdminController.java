package org.example.controller;

import org.example.entity.ComandaEntity;
import org.example.repository.ComandaRepository;
import org.example.service.NotificationService;
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

    private final ComandaRepository comandaRepository;
    private final NotificationService notificationService;

    public AdminController(ComandaRepository comandaRepository,
                           NotificationService notificationService) {
        this.comandaRepository = comandaRepository;
        this.notificationService = notificationService;
    }

    @GetMapping("/admin")
    public String navigateToAdminsPage(Model model) {
        List<ComandaEntity> comenzi = comandaRepository.findAll();
        model.addAttribute("comenzi", comenzi);
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

        Optional<ComandaEntity> comandaOpt = comandaRepository.findById(id);

        if (comandaOpt.isPresent()) {
            ComandaEntity comanda = comandaOpt.get();
            String stareVeche = comanda.getStare();
            String numeClient = comanda.getClient();

            if (stareVeche.equalsIgnoreCase(stareNoua)) {
                redirectAttributes.addFlashAttribute("error",
                        "Starea selectată este aceeași cu starea actuală!");
                return "redirect:/admin";
            }

            // Actualizează starea
            comanda.setStare(stareNoua);
            comandaRepository.save(comanda);

            // 🔔 TRIMITE NOTIFICARE CLIENTULUI
            notificationService.notificaSchimbareStare(numeClient, id, stareVeche, stareNoua);

            redirectAttributes.addFlashAttribute("success",
                    "Comanda #" + id + " a fost actualizată din '" + stareVeche + "' în '" + stareNoua + "'. Clientul " + numeClient + " a fost notificat!");
        } else {
            redirectAttributes.addFlashAttribute("error",
                    "Comanda #" + id + " nu a fost găsită!");
        }

        return "redirect:/admin";
    }
}
