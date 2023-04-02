package lab_rab.lab2.threads;

import lab_rab.lab2.interfaces.IVehicle;

public class Thread4 implements Runnable{
    private IVehicle vehicle;

    public Thread4 (IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void run() {
        try {
                System.out.println("Нить выводит марку " + vehicle.getBrand());
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

}