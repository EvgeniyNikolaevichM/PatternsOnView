package lab_rab.lab2.state.interfaces;

import lab_rab.lab2.state.ControlRole;

public interface IObserver {
    void update(ControlRole role);
    void subscribe(IObservable provider);
}
