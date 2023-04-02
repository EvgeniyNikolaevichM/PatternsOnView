package lab_rab.lab2.threads;

import lab_rab.lab2.Car;
import lab_rab.lab2.interfaces.IVehicle;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Thread5BlockingQueue implements Runnable {
    private String filePath;
    private ArrayBlockingQueue arrayBlockingQueue;

    public Thread5BlockingQueue (String filePath, ArrayBlockingQueue arrayBlockingQueue) {
        this.filePath = filePath;
        this.arrayBlockingQueue = arrayBlockingQueue;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String brand = reader.readLine();
            IVehicle car = new Car(brand,2);
            reader.close();
            //arrayBlockingQueue.put(car);
            arrayBlockingQueue.add(car);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } //catch (IllegalStateException e) {
           // throw new RuntimeException(e);
        //}
    }
}
