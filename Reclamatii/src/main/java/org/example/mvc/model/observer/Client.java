package org.example.mvc.model.observer;

public class Client implements Observer {

    private String name;

    public Client(String name) {
        this.name = name;
    }

    @Override
    public void update(String mesaj) {
        System.out.println("Clientul " + name + " a primit mesajul: " + mesaj);
    }
}
