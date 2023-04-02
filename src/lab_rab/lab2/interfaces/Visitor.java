package lab_rab.lab2.interfaces;

import lab_rab.lab2.Car;
import lab_rab.lab2.Motorcycle;
import lab_rab.lab2.exceptions.NoSuchModelNameException;

public interface Visitor {
    void visit(Car car);
    void visit(Motorcycle motorcycle) throws NoSuchModelNameException;
}
