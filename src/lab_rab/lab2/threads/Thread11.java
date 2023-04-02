package lab_rab.lab2.threads;

import lab_rab.lab2.interfaces.IVehicle;

public class Thread11 extends Thread{
    private IVehicle vehicle;

    public Thread11(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void run() {
        try {
            for (int i =0; i < vehicle.getModelsSize(); i++) {
                System.out.println("Нить выводит название модели " + vehicle.getAllModelsNames()[i]);
            }
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

}