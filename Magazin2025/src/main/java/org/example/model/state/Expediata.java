package org.example.model.state;

public class Expediata implements State{

    @Override
    public void changeState() {
        System.out.println("Comanda a fost expediata");
    }
}
