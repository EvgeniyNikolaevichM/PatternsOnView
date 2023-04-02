package lab_rab.lab2;
//
import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.exceptions.ModelPriceOutOfBoundsException;
import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.interfaces.IVehicle;
import lab_rab.lab2.interfaces.Visitor;

import java.lang.System;
import java.io.Serializable;
import java.util.Objects;
import java.util.Arrays;



public class Motorcycle implements IVehicle, Serializable, Cloneable {
    private class Model implements Serializable, Cloneable {
        private String name = null;
        private double price = Double.NaN;
        Model prev = null;
        Model next = null;

        Model(Model next, Model prev, String name, double price) {
            this.name = name;
            this.price = price;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "model: " + name + " price: " + price;
        }

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        double getPrice() {
            return this.price;
        }

        void setPrice(double price) {
            if (price <= 0)
                throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
            else
                this.price = price;
        }

        public String getName() {
            return this.name;
        }

        void setName(String name) {
            this.name = name;
        }

        public int hashCode() {
            int result = name == null ? 0 : name.hashCode();
            result = (int) (result + price);
            return result;
        }

        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }

    private String brand;
    private int size = 0;

    private Model head = new Model("Moto0", 500);
    {
        size = 1;
        head.prev = head;
        head.next = head;
    }

    private transient long lastModified;

    {
        this.lastModified = System.currentTimeMillis();
    }

    public Motorcycle(String brand) {
        this.brand = brand;
    }

    public Motorcycle(String brand, int modelsSize) {
        this.brand = brand;
        for (int i = 0; i < modelsSize; i++) {
            try {
                addModel(brand + Integer.toString(i+1), i+100);
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void setModelName(String modelName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (isModelExists(newName))
            throw new DuplicateModelNameException(newName);
        Model model = findModelByName(modelName);
        model.setName(newName);
    }

    @Override
    public String[] getAllModelsNames() {
        String[] names = new String[size];
        if (!isEmpty()) {
            Model node = this.head;
            for (int i = 0; i < size; node = node.next, i++)
                names[i] = node.name;
        }
        return names;
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
       double[] prices = new double[size];
        Model node = this.head;
        for (int i = 0; i < size; node = node.next, i++)
            prices[i] = node.price;
        return prices;
    }

    private Model findModelByName(String modelName) throws NoSuchModelNameException {
        Model result = getModelByName(modelName);
        if (result == null)
            throw new NoSuchModelNameException(modelName);
        return result;
    }

    private Model getModelByName(String modelName){
        Model result = null;
            if (this.head.name.equals(modelName)) {
                result = this.head;
            } else {
                for (Model node = this.head.next; node != this.head; node = node.next)
                    if (node.name.equals(modelName)) {
                        result = node;
                        break;
                    }
            }
        return result;
    }

    private boolean isModelExists(String modelName){
      return getModelByName(modelName) != null;
    }

    private boolean isEmpty() {
        return this.size == 0;
    }

    public Model getModelByIndex(int index) {
        Model m;
        m = head;
        int i = 1;
        while (i <= index) {
            m = m.next;
            ++i;
        }
        return m;
    }



    @Override
    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price <= 0)
            throw new ModelPriceOutOfBoundsException("Model price must be greater than zero!");
        if (getModelByName(modelName) == null) {
                Model model = new Model(modelName, price);
                model.next = head;
                model.prev = head.prev;
                model.prev.next = model;
                head.prev = model;
                size++;
            }
        else {
            throw new DuplicateModelNameException(modelName);
        }
        this.lastModified = System.currentTimeMillis();
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        if (head == null)
            return;
        Model modelToDelete = findModelByName(modelName);

        if (modelToDelete.next == modelToDelete.prev)
            head = null;
        else {
            modelToDelete.prev.next = modelToDelete.next;
            modelToDelete.next.prev = modelToDelete.prev;
        }
        size--;
        this.lastModified = System.currentTimeMillis();
    }


    @Override
    public int getModelsSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Марка ").append(getBrand()).append("\n");
            for (int i = 0; i < getAllModelsNames().length; i++) {
                stringBuffer.append("Модель ").append(getAllModelsNames()[i]).append("\n");
            }
            for (int j = 0; j < getAllModelsPrices().length; j++) {
                stringBuffer.append("Цена модели ").append(getAllModelsPrices()[j]).append("\n");
            }
            return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Motorcycle)) return false;
        if (Objects.equals(this.brand, ((Motorcycle) o).brand)) {
            return Arrays.equals(getAllModelsPrices(), ((Motorcycle) o).getAllModelsPrices())
                    && Arrays.equals(getAllModelsNames(), ((Motorcycle) o).getAllModelsNames());
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(head, size, brand);
    }

    public String getModel(int i) {
        return getModelByIndex(i).getName() + " " + getModelByIndex(i).getPrice();
    }

    @Override
    public Motorcycle clone() throws CloneNotSupportedException {
        Motorcycle clone = (Motorcycle) super.clone();
        double[] prices = getAllModelsPrices();
        String[] names = getAllModelsNames();
        clone.head = new Model(names[0], prices[0]);
        clone.head.prev = clone.head;
        clone.head.next = clone.head;
        for (int i = 1; i < size; i++) {
                Model node = new Model(names[i], prices[i]);
                node.prev = clone.head.prev;
                node.next = clone.head;
                clone.head.prev.next = node;
                clone.head.prev = node;
            }
            clone.size = this.size;
            return clone;
        }


    public void accept(Visitor visitor) throws NoSuchModelNameException {
        visitor.visit(this);
    }


}
