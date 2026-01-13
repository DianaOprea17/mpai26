package org.example.mvp.state;

public class SolicitareAcceptata implements State {

    @Override
    public void next(Interventie solicitare) {
        solicitare.setStare(new EchipaPlecataSpreClient(), "Echipa a plecat spre client.");
    }
}
