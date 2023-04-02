package lab_rab.lab2.observer.interfaces;


import lab_rab.lab2.observer.ControlRole;

public interface IObservable {
    void addObserver(IObserver observer);
    void notify(ControlRole role);
}
