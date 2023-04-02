package lab_rab.lab2.threads;

import lab_rab.lab2.interfaces.IVehicle;

public class Thread12 extends Thread{
    private IVehicle vehicle;

    public Thread12(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void run() {
        try {
            for (int i =0; i < vehicle.getModelsSize(); i++) {
                System.out.println("Нить выводит цену модели " + vehicle.getAllModelsPrices()[i]);
            }
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

}