package org.example.mvp.state;

public class SolicitareTransmisa implements State {


    @Override
    public void next(Interventie solicitare) {
        solicitare.setStare(
                new SolicitareAcceptata(),
                "Solicitarea a fost acceptată. Echipa este disponibilă."
        );
    }
}
