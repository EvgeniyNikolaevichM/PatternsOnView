package lab_rab.lab2;

import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.ModelPriceOutOfBoundsException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.interfaces.Command;
import lab_rab.lab2.interfaces.IVehicle;
import lab_rab.lab2.interfaces.Visitor;

import java.io.Serializable;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class Car implements IVehicle, Serializable, Cloneable{
    private String brand;
    private Model[] models;
    private Command command = new RowCommand();
    private AutoIterator autoIterator;

    public Car(String brand, int modelsSize) {
        this.brand = brand;
        this.models = new Model[modelsSize];

        for (int i = 0; i < modelsSize; i++) {
            models[i] = new Model("Model[" + i + "]", 100);
        }
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand (String brand) {
        this.brand = brand;
    }

    private class Model implements Serializable, Cloneable {
        private String name;
        private double price;

        @Override
        public String toString() {
            return name + " " + price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            if (price <= 0)
                throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
            else
                this.price = price;
        }

        public Model (String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (!(o instanceof Car.Model)) return false;
            if (!Objects.equals(this.getName(), ((Car.Model) o).getName()))
                return false;
            return Objects.equals(this.getPrice(), ((Car.Model) o).getPrice());
        }


        public int hashCode() {
            int result = name == null ? 0 : name.hashCode();
            result = (int) (result + price);
            return result;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }

    }

    @Override
    public void setModelName(String modelName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (getModelsSize() != 0 && indexOfModel(newName) != -1)
            throw new DuplicateModelNameException(newName);
        Model model = findModelByName(modelName);
        model.setName(newName);
    }


    @Override
    public String[] getAllModelsNames() {
        String[] allNames = new String[models.length];
        for (int i = 0; i < models.length; i++)
            allNames[i] = models[i].getName();
        return allNames;
    }


    @Override
    public double getModelPrice(String modelName) throws NoSuchModelNameException {
        Model model = findModelByName(modelName);
        return model.getPrice();
    }


    @Override
    public void setModelPrice(String modelName, double price) throws NoSuchModelNameException {
        if (price <= 0)
            throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        Model model = findModelByName(modelName);
        model.setPrice(price);
    }


    @Override
    public double[] getAllModelsPrices() {
        double[] prices = new double[models.length];
        for (int i = 0; i < models.length; i++) {
            prices[i] = models[i].getPrice();
        }
        return prices;
    }


    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        for (Model model : models)
            if (Objects.equals(model.getName(), modelName)) throw new DuplicateModelNameException(modelName);
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, price);
    }


    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        int size = getModelsSize();
        if (size == 0)
            return;
        int index = indexOfModel(modelName);
        if (index == -1)
            throw new NoSuchModelNameException(modelName);

        Model[] newModels = new Model[size - 1];
        System.arraycopy(this.models, 0, newModels, 0, index);
        if (size != index) {
            System.arraycopy(this.models, index + 1, newModels, index, size - index - 1);
        }
        this.models = newModels;
    }


    @Override
    public int getModelsSize() {
        return models.length;
    }

    private Model findModelByName(String modelName) throws NoSuchModelNameException {
        int index = indexOfModel(modelName);
        if (index == -1)
            throw new NoSuchModelNameException(modelName);
        return models[index];
    }

    private int indexOfModel(String modelName) {
            for (int i = 0; i < getModelsSize(); i++) {
                if (this.models[i].getName().equals(modelName))
                    return i;
            }
        return -1;
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Car)) return false;
        if (Objects.equals(this.brand, ((Car) o).brand)) {
            return Arrays.equals(getAllModelsPrices(),((Car) o).getAllModelsPrices())
                    && Arrays.equals(getAllModelsNames(),((Car) o).getAllModelsNames());
        }
        return false;
    }

    public int hashCode() {
        int result = brand == null ? 0 : brand.hashCode();
        result = 31 * result + Arrays.hashCode(models);
        return result;
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
        Car clone = (Car) super.clone();
        clone.models = this.models.clone();
        for (int i = 0; i < models.length; i++) {
            clone.models[i] = this.models[i].clone();
        }
        return clone;
    }

    public Model getModelByIndex(int i) {
        return models[i];
    }


    public void print(FileWriter fileWriter) throws IOException {
        if (this.command != null) {
            command.write_to_file(this, fileWriter);
        } else {
            System.out.println("Команда не задана");
        }
    }

    public void setPrintCommand(Command command) {
        this.command = command;
    }


    public String getModel(int i) {
        return models[i].getName() + " " + models[i].getPrice();
    }


    public AutoIterator iterator() {
        return new AutoIterator();
    }

    class AutoIterator implements java.util.Iterator {

        int index;

        @Override
        public boolean hasNext() {
            return index < getModelsSize();
        }

        @Override
        public Model next() {
            return models[index++];
        }

        @Override
        public void remove() {
            deleteByIndex(index);
        }
    }

    public void deleteByIndex(int i) {
        Model[] copy = new Model[models.length - 1];
        System.arraycopy(models, 0, copy, 0, i);
        System.arraycopy(models, i + 1, copy, i, models.length - i - 1);
        models = copy;
    }


    public static class Memento implements Serializable {

        private static final ByteArrayOutputStream state = new ByteArrayOutputStream(64);

        public static void setAuto(Car car) throws IOException {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(state);
            objectOutputStream.writeObject(car);
            state.close();
            objectOutputStream.close();
        }


        public static Car getAuto() throws IOException, ClassNotFoundException {
            byte[] buffer = state.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            byteArrayInputStream.close();
            objectInputStream.close();
            return (Car) objectInputStream.readObject();

        }
    }

    public void createMemento() throws IOException {
        Memento.setAuto(this);
    }

    public Car setMemento() throws IOException, ClassNotFoundException {
        return Memento.getAuto();
    }


    public void accept(Visitor visitor) throws NoSuchModelNameException {
        visitor.visit(this);
    }



}
