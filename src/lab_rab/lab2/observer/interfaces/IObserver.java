package lab_rab.lab2.observer.interfaces;


import lab_rab.lab2.observer.ControlRole;

public interface IObserver {
    void update(ControlRole role);
    void subscribe(IObservable provider);
}
