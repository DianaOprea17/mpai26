package org.example.mvp.state;

public class EchipaPlecataSpreClient implements State {

    @Override
    public void next(Interventie solicitare) {
        System.out.println("Stare finală. Nu mai există tranziții.");
    }
}
