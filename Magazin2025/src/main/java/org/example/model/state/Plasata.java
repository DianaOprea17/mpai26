package org.example.model.state;

public class Plasata implements State{
    @Override
    public void changeState() {
        System.out.println("Comanda a fost plasata");
    }
}
