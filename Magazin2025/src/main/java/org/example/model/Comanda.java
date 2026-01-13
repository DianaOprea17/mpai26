package org.example.model;

import org.example.model.observer.Observer;
import org.example.model.observer.Subiect;
import org.example.model.state.State;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda {

    private Long id;
    private String name;
    private LocalDate orderDate;
    private State state;

    private List<Observer> observers = new ArrayList<>();

    public String getStateName() {
        return state.getClass().getSimpleName();
    }

    public Comanda(Long id, String name, LocalDate orderDate, State state) {
        this.id = id;
        this.name = name;
        this.orderDate = orderDate;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
