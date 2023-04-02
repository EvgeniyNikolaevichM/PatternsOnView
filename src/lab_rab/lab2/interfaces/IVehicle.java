package lab_rab.lab2.interfaces;

import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;

import java.io.OutputStream;
import java.io.Serializable;

public interface IVehicle  {
    void setBrand(String brand);
    void setModelName(String modelName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;
    void setModelPrice(String modelName, double price) throws NoSuchModelNameException;
    void addModel(String modelName, double price) throws DuplicateModelNameException;
    void deleteModel(String modelName) throws NoSuchModelNameException;

    String getBrand();

    int getModelsSize();
    double getModelPrice(String modelName) throws NoSuchModelNameException;

    String[] getAllModelsNames();
    double[] getAllModelsPrices();
}
