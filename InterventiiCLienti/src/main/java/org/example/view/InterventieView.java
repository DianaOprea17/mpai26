package org.example.view;

import org.example.mvp.entity.InterventieEntity;
import org.example.mvp.state.Interventie;
import org.springframework.ui.Model;

import java.util.List;

public interface InterventieView {
    void afiseazaInterventii(Model model, List<InterventieEntity> interventii);
    void afiseazaMesaj(Model model, String mesaj);
    void afiseazaEroare(Model model, String eroare);
}
