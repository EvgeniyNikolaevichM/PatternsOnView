package lab_rab.lab2.interfaces;

public interface TransportFactory {
    IVehicle createInstance(String brand, int modelsSize);
}
