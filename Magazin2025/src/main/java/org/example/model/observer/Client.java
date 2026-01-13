package org.example.model.observer;

public class Client implements Observer{

    private String name;
    private String age;

    public Client(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void update(String message) {
        System.out.println("Clientul" + name + "a fost notificat: " + message);
    }
}
