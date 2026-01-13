package org.example.mvc.model.observer;

public interface Subiect {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
