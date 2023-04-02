package lab_rab.lab2;

import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.ModelPriceOutOfBoundsException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.interfaces.IVehicle;

import java.io.Serializable;
import java.io.*;
import java.util.*;

public class QuadBike implements IVehicle {
    private String brand;
    private ArrayList<Model> modelArrayList;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    private class Model implements Serializable, Cloneable {
        private String name;
        private double price;

        public String getName() {
            return name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    public QuadBike(String brand, int modelsSize) {
        this.brand = brand;
        modelArrayList = new ArrayList<>();
        for (int i = 0; i < modelsSize; i++)
            modelArrayList.add(new Model(brand + i, 200 + i));
    }

    public void setModelName(String modelName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        double oldPrice = 0;
        for (Model model:modelArrayList)
            if (Objects.equals(model.getName(), modelName))
                oldPrice = model.getPrice();
        modelArrayList.removeIf(model -> Objects.equals(model.getName(), modelName));
        modelArrayList.add(new Model(newName,oldPrice));

    }

    public String[] getAllModelsNames() {
        int length = modelArrayList.size();
        String[] NamesArray = new String[length];
        for (int i = 0; i < length; i++) {
            NamesArray[i] = modelArrayList.get(i).getName();
        }
        return NamesArray;
    }

    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        for (Model model : modelArrayList)
            if (model.name.equals(modelName))
                return model.price;
        throw new NoSuchModelNameException(modelName);

    }

    public void setModelPrice(String modelName, double price) throws NoSuchModelNameException {
        boolean isChange = true;
        if (price < 0) throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        for (Model model : modelArrayList)
            if (Objects.equals(model.getName(), modelName)) {
                model.setPrice(price);
                isChange = false;
                break;
            }
        if (isChange) throw new NoSuchModelNameException(modelName);
    }

    public double[] getAllModelsPrices() {
        int length = modelArrayList.size();
        double[] PricesArray = new double[length];
        for (int i = 0; i < length; i++)
            PricesArray[i] = modelArrayList.get(i).getPrice();
        return PricesArray;
    }


    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        for (Model model : modelArrayList)
            if (Objects.equals(model.getName(), modelName)) throw new DuplicateModelNameException(modelName);
        modelArrayList.add(new Model(modelName, price));

    }

    public void deleteModel(String modelName) throws NoSuchModelNameException {
        boolean flag = true;
        int length = getModelsSize();
        for (int i =1; i < length; i++) {
            if (Objects.equals(modelArrayList.get(i).getName(), modelName)) {
                this.modelArrayList.remove(i);
                flag=false;
            }
        }
        if (flag) throw new NoSuchModelNameException(modelName);
    }

    public int getModelsSize() {
        return modelArrayList.size();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
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
