package org.example.mvc.model.state;

public class Inregistrata implements State {
    @Override
    public void changeState() {
        System.out.println("Comanda a fost inregistrata");
    }
}
