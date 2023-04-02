package lab_rab.lab2.state.interfaces;

import lab_rab.lab2.state.ControlRole;

public interface IObservable {
    void addObserver(IObserver observer);
    void notify(ControlRole role);
}
