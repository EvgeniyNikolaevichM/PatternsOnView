package lab_rab.lab2;

import lab_rab.lab2.exceptions.NoSuchModelNameException;
import lab_rab.lab2.exceptions.DuplicateModelNameException;
import lab_rab.lab2.interfaces.IVehicle;
import lab_rab.lab2.interfaces.TransportFactory;

import javax.crypto.spec.IvParameterSpec;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;

public class Vehicles {

    private static TransportFactory factory = new AutoFactory();

    public static void setTransportFactory(TransportFactory transportFactory) {
        factory = transportFactory;
    }

    public static IVehicle createInstance(String brand, int modelsSize) {
        return factory.createInstance(brand, modelsSize);
    }


    public static double getAveragePrice(IVehicle vehicle) {
        if (vehicle.getModelsSize() == 0)
            return 0;
        double sum = 0;
        for (double price :
                vehicle.getAllModelsPrices()) {
            sum += price;
        }
        return sum / vehicle.getModelsSize();
    }


    public static void printVehicle(IVehicle vehicle){
        System.out.println(vehicle.getBrand());
        try {
            printPriceList(vehicle);
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
    }

    public static void printPriceList(IVehicle vehicle) throws NoSuchModelNameException {
        for (String modelName : vehicle.getAllModelsNames())
            System.out.println("model: " + modelName + " price: " + vehicle.getModelPrice(modelName));
    }

    public static void outputVehicle(IVehicle v, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(v.getClass().getName());
        dos.writeUTF(v.getBrand());
        int len = v.getModelsSize();
        dos.writeInt(len);
        try {
            for (int i = 0; i < len; i++) {
                dos.writeUTF(v.getAllModelsNames()[i]);
                dos.writeDouble(v.getAllModelsPrices()[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Файл записан. Длина в байтах = " + len);
    }

    public static IVehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException {
        DataInputStream dis = new DataInputStream(in);
        IVehicle res = null;
        String cl = dis.readUTF();
        String brand = dis.readUTF();
        int len = dis.readInt();
        if (cl.equals("Motorcycle"))
            res = new Motorcycle(brand, 0);
        else res = new Car(brand, 0);
        for (int i = 0; i < len; i++) {
            res.addModel(dis.readUTF(), dis.readDouble());
        }
        return res;
    }

    public static void writeVehicle(IVehicle v, Writer out) {
        try {
            PrintWriter printWriter = new PrintWriter(out);
            if (v instanceof Car) printWriter.println("Car");
            else printWriter.println("Motorcycle");
            printWriter.println(v.getBrand());
            printWriter.println(v.getModelsSize());
            for (int i = 0; i < v.getModelsSize(); i++) {
                printWriter.println(v.getAllModelsNames()[i]);
                printWriter.println(v.getAllModelsPrices()[i]);
            }
            printWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static IVehicle readVehicle(Reader in) {
        IVehicle vehicle;
        try {
            BufferedReader bufferedReader = new BufferedReader(in);
            String cl = bufferedReader.readLine();
            String brand = bufferedReader.readLine();
            int count = Integer.parseInt(bufferedReader.readLine());
            String[] models = new String[count];
            double[] prices = new double[count];
            if (cl.equals("Motorcycle"))
                vehicle = new Motorcycle(brand, 0);
            else vehicle = new Car(brand, 0);
            for (int i = 0; i < count; i++) {
                models[i] = bufferedReader.readLine();
                prices[i] = Double.parseDouble(bufferedReader.readLine());
                vehicle.addModel(models[i], prices[i]);
            }
        } catch (IOException | DuplicateModelNameException ex) {
            throw new RuntimeException(ex);
        }
        return vehicle;
    }

    public static IVehicle createByReflection(IVehicle vehicle, String brand, int size) {
        try {
            Class vehicleClass = vehicle.getClass();
            IVehicle newVehicle = (IVehicle) vehicleClass.getConstructor(String.class, int.class).newInstance(brand, size);
            return newVehicle;
        } catch (Exception e) {
            System.out.println("Ошибка");
            return null;
        }
    }

    public static double getAveragePrices(IVehicle...vehicles) {
        double result = 0;
        for (IVehicle vehicle : vehicles) {
            result+=getAveragePrice(vehicle)/vehicles.length;
        }
        return result;
    }

    public static void writesVehicle (IVehicle vehicle, Writer out) throws IOException{
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.printf("%s\n",vehicle.getClass().getName());
        printWriter.printf("%s\n",vehicle.getBrand());
        int lenght = vehicle.getModelsSize();
        printWriter.printf("%s\n",lenght);
        try {
            for (int i = 0; i < lenght; i++) {
                printWriter.printf("%s\n",vehicle.getAllModelsNames()[i]);
                printWriter.printf("%s\n",vehicle.getAllModelsPrices()[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }printWriter.flush();
    }



    public static IVehicle readsVehicle(Reader in) {
        IVehicle vehicle;
        try {
            Scanner scanner = new Scanner(in);
            String cl = scanner.next();
            String brand = scanner.next();
            int count = scanner.nextInt();
            String[] models = new String[count];
            double[] prices = new double[count];
            if (cl.equals("Motorcycle"))
                vehicle = new Motorcycle(brand, 0);
            else vehicle = new Car(brand, 0);
            for (int i = 0; i < count; i++) {
                models[i] = scanner.next();
                prices[i] = scanner.nextDouble();
                vehicle.addModel(models[i], prices[i]);
            }
        } catch (DuplicateModelNameException ex) {
            throw new RuntimeException(ex);
        }
        return vehicle;
    }

    public static Decorator getSynchronizedVehicle(IVehicle vehicle) {
        return new Decorator(vehicle);
    }


}



