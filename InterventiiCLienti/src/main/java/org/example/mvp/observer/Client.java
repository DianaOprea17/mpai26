package org.example.mvp.observer;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class Client implements Observer {

    private String nume;

    public Client(String nume) {
        this.nume = nume;
    }

    @Override
    public void update(String mesaj) {
        System.out.println("Clientul " + nume + " a primit notificarea: " + mesaj);
    }

}
