package org.example.mvc.controller;


import org.example.mvc.entity.ReclamatieEntity;
import org.example.mvc.repository.ReclamatieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ReclamatieRepository reclamatieRepository;

    public ClientController(ReclamatieRepository reclamatieRepository) {
        this.reclamatieRepository = reclamatieRepository;
    }

    @GetMapping
    public String clientPage() {
        return "client/index";
    }

    @GetMapping("/reclamatii")
    public String cautaReclamatie(@RequestParam("numeClient") String numeClient, Model model) {
        List<ReclamatieEntity> reclamatii = reclamatieRepository.findByClient(numeClient);
        model.addAttribute("reclamatii", reclamatii);
        model.addAttribute("numeClient", numeClient);
        return "client/reclamatii";
    }

}
