package org.example.controller;

import org.example.entity.ComandaEntity;
import org.example.repository.ComandaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ComandaRepository comandaRepository;

    public ClientController(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    @GetMapping
    public String clientPage() {
        return "client/index";
    }

    @GetMapping("/comenzi")
    public String cautaComenzi(@RequestParam("numeClient") String numeClient, Model model) {
        List<ComandaEntity> comenzi = comandaRepository.findByClient(numeClient);
        model.addAttribute("comenzi", comenzi);
        model.addAttribute("numeClient", numeClient);
        return "client/comenzi";
    }


}
