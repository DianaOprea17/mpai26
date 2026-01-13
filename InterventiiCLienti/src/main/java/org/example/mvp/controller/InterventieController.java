package org.example.mvp.controller;

import org.example.mvp.entity.InterventieEntity;

import org.example.presenter.InterventiePresenter;
import org.example.view.InterventieView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/interventii")
public class InterventieController implements InterventieView {

    private final InterventiePresenter presenter;

    public InterventieController(InterventiePresenter presenter) {
        this.presenter = presenter;
    }

    @GetMapping
    public String paginaInterventii(Model model) {
        presenter.afiseazaToateInterventiile(model);
        return "interventie/index";
    }

    @PostMapping("/modifica-stare/{id}")
    public String modificaStare(@PathVariable("id") Long id,
                                @RequestParam("stareNoua") String stareNoua,
                                RedirectAttributes redirectAttributes) {
        presenter.modificaStare(id, stareNoua, redirectAttributes);
        return "redirect:/interventii";
    }

    @GetMapping("/filtru")
    public String filtreaza(@RequestParam(required = false) String stare, Model model) {
        presenter.filtreazaDupaStare(stare, model);
        return "interventie/index";
    }

    @GetMapping("/cauta")
    public String cauta(@RequestParam String q, Model model) {
        presenter.cautaDupaClient(q, model);
        return "interventie/index";
    }

    @Override
    public void afiseazaInterventii(Model model, List<InterventieEntity> interventii) {
        model.addAttribute("interventii", interventii);
    }

    @Override
    public void afiseazaMesaj(Model model, String mesaj) {
        model.addAttribute("mesaj", mesaj);
    }

    @Override
    public void afiseazaEroare(Model model, String eroare) {
        model.addAttribute("eroare", eroare);
    }
}

