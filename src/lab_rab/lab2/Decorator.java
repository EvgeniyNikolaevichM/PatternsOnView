package lab_rab.lab2;

import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.interfaces.IVehicle;

public class Decorator implements IVehicle{
    private IVehicle vehicle;
    public Decorator(IVehicle vehicle) {
        this.vehicle=vehicle;
    }
    @Override
    public synchronized String getBrand() {
        return vehicle.getBrand();
    }

    @Override
    public synchronized void setBrand(String brand) {
        vehicle.setBrand(brand);
    }

    @Override
    public synchronized void setModelName(String modelName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        vehicle.setModelName(modelName, newName);
    }

    @Override
    public synchronized String[] getAllModelsNames() {
        return vehicle.getAllModelsNames();
    }

    @Override
    public synchronized double getModelPrice(String modelName) throws NoSuchModelNameException {
        return vehicle.getModelPrice(modelName);
    }

    @Override
    public synchronized void setModelPrice(String modelName, double price) throws NoSuchModelNameException {
        vehicle.setModelPrice(modelName, price);
    }

    @Override
    public synchronized double[] getAllModelsPrices() {
        return vehicle.getAllModelsPrices();
    }

    @Override
    public synchronized void addModel(String modelName, double price) throws DuplicateModelNameException {
        vehicle.addModel(modelName,price);
    }

    @Override
    public synchronized void deleteModel(String modelName) throws NoSuchModelNameException {
        vehicle.deleteModel(modelName);
    }

    @Override
    public synchronized int getModelsSize() {
        return vehicle.getModelsSize();
    }
}
    