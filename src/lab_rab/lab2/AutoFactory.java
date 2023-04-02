package lab_rab.lab2;

import lab_rab.lab2.interfaces.IVehicle;
import lab_rab.lab2.interfaces.TransportFactory;

public class AutoFactory implements TransportFactory {
    public IVehicle createInstance(String brand, int modelsSize) {
        return new Car(brand, modelsSize);
    }
}
    