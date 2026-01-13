package org.example.mvc.model.state;

public class InAnaliza implements State {
    @Override
    public void changeState() {
        System.out.println("Comanda este in analiza");
    }
}
