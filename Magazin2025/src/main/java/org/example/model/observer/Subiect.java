package org.example.model.observer;

public interface Subiect {

    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
