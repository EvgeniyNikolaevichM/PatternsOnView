package lab_rab.lab2.DAO;

import lab_rab.lab2.interfaces.IVehicle;

import java.io.*;

public class Serialize implements DataTypeGetting {
    private IVehicle vehicle;
    public Serialize() {}
    @Override
    public void write(IVehicle vehicle){
        try {
            this.vehicle = vehicle;
            FileOutputStream fileOutputStream = new FileOutputStream ("serialize.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(vehicle);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи в файл " + e);
        }
    }
    @Override
    public void read() throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream("serialize.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            vehicle = (IVehicle) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка прочтения файла " + e);
        }
    }
    @Override
    public void print() {
        String brand = vehicle.getBrand();
        double[] prices = vehicle.getAllModelsPrices();
        String[] models = vehicle.getAllModelsNames();
        System.out.println("Марка: " + brand + "  Количество моделей: " + vehicle.getModelsSize());
        for (int i = 0; i <  vehicle.getModelsSize(); i++) {
            System.out.println(models[i] + "  " + prices[i]);
        }
    }
    public IVehicle getVehicle() {
        return vehicle;
    }
}
    