package org.example.model.state;

public class Livrata implements State{
    @Override
    public void changeState() {
        System.out.println("Comanda a fost livrata");
    }
}
