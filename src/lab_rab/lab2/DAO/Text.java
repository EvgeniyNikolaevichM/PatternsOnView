package lab_rab.lab2.DAO;

import lab_rab.lab2.interfaces.IVehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Text implements DataTypeGetting {
    private IVehicle vehicle;
    private String mark;
    private int count;
    private double[] prices ;
    private String[] models ;

    @Override
    public void write(IVehicle vehicle) {
        this.vehicle = vehicle;
        try {
            FileWriter writer = new FileWriter("text.txt");
            writer.write("" + vehicle.getBrand());
            writer.write("\n" + vehicle.getModelsSize());
            for (int i = 0; i < vehicle.getModelsSize(); i++) {
                writer.write("\n" +  vehicle.getAllModelsNames()[i]);
                writer.write("\n" +  vehicle.getAllModelsPrices()[i]);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка записи в файл " + e);
        }
    }
    @Override
    public void read() throws IOException {
        try{
            FileReader fileReader = new FileReader("text.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            mark = bufferedReader.readLine();
            count = Integer.valueOf(bufferedReader.readLine());
            models = new String[count];
            prices = new double[count];
            for(int i = 0; i < count; i++){
                models[i] = bufferedReader.readLine();
                prices[i] = Double.valueOf(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка прочтения файла " + e);
        }
    }
    @Override
    public void print() {
        System.out.println("Марка: " + mark + "  Количество моделей: " + count);
        for (int i = 0; i <  count; i++) {
            System.out.println(models[i] + "  " + prices[i]);
        }
    }
    public IVehicle getVehicle() {
        return vehicle;
    }
}
    