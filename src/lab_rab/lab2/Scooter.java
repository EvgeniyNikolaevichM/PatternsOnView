package lab_rab.lab2;

import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.ModelPriceOutOfBoundsException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.interfaces.IVehicle;

import java.io.Serializable;
import java.io.*;
import java.util.Arrays;
import java.util.*;
import java.util.Objects;


public class Scooter implements IVehicle {
    private String brand;
    double price;
    private HashMap<String,Double> models;

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Scooter(String brand, int modelsSize) {
        this.brand = brand;
        models = new HashMap<>();
        for (int i = 0; i < modelsSize; i++)
            models.put(brand+i, (double) (200+i));
    }
    @Override
    public void setModelName(String modelName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        boolean found = models.containsKey(newName);
        boolean found2 = models.containsKey(modelName);
        if (!found2) throw new NoSuchModelNameException(modelName);
        Double oldPrice;
        oldPrice = models.get(modelName);
        if (found) throw new DuplicateModelNameException(newName);
        else models.remove(modelName);
        models.put(newName,oldPrice);
    }
    @Override
    public String[] getAllModelsNames() {
        Set<String> keys = models.keySet();
        return keys.toArray(new String[0]);
    }
    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        boolean found = models.containsKey(modelName);
        if (!found) throw new NoSuchModelNameException(modelName);
        models.get(modelName);
        return models.get(modelName);
    }
    @Override
    public void setModelPrice(String modelName, double price) throws NoSuchModelNameException {
        if (price<0) throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        boolean found = models.containsKey(modelName);
        if (!found) throw new NoSuchModelNameException(modelName);
        models.replace(modelName,price);
    }

    @Override
    public double[] getAllModelsPrices() {
        double[] pricesArray = new double[models.size()];
        ArrayList<Double> values = new ArrayList<>(models.values());
        for (int i = 0; i<models.size();i++)
            pricesArray[i] = values.get(i);
        return pricesArray;
    }

    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price<0) throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        boolean found = models.containsKey(modelName);
        if (found) throw new DuplicateModelNameException(modelName);
        models.put(modelName,price);
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        boolean found = models.containsKey(modelName);
        if (found) models.remove(modelName);
        else throw new NoSuchModelNameException(modelName);
    }

    @Override
    public int getModelsSize() {
        return models.size();
    }
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("Марка ").append(getBrand()).append("\n");
        for (int i = 0; i < getAllModelsNames().length; i++) {
            stringBuffer.append("Модель ").append(getAllModelsNames()[i]).append("\n");
        }
        for (int i = 0; i < getAllModelsPrices().length; i++) {
            stringBuffer.append("Цена модели ").append(getAllModelsPrices()[i]).append("\n");
        }
        return stringBuffer.toString();
    }
}
