package org.example.mvp.controller;

import org.example.mvp.entity.InterventieEntity;
import org.example.mvp.state.Interventie;
import org.example.presenter.InterventiePresenter;
import org.example.view.ClientView;
import org.example.view.InterventieView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/client")
public class ClientController implements ClientView {

    private final InterventiePresenter presenter;

    public ClientController(InterventiePresenter presenter) {
        this.presenter = presenter;
    }

    @GetMapping
    public String paginaClient() {
        return "client/index";
    }

    @GetMapping("/interventii")
    public String cautaInterventii(@RequestParam("numeClient") String numeClient, Model model) {
        presenter.cautaDupaClient(numeClient, model);
        return "client/interventii";
    }

    @Override
    public void afiseazaInterventii(Model model, List<InterventieEntity> interventii) {
        model.addAttribute("interventii", interventii);
    }

    @Override
    public void afiseazaMesaj(Model model, String mesaj) {
        model.addAttribute("mesaj", mesaj);
    }
}