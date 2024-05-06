package tech.neo.observer;

public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

}
