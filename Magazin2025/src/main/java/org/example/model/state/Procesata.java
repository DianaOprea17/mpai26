package org.example.model.state;

public class Procesata implements State{
    @Override
    public void changeState() {
        System.out.println("Comanda a fost procesata");
    }
}
