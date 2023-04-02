package lab_rab.lab2;

import lab_rab.lab2.interfaces.ChainOfResponsibility;
import lab_rab.lab2.interfaces.IVehicle;

import java.io.FileWriter;
import java.io.IOException;

public class ColumnChain implements ChainOfResponsibility {
    private ChainOfResponsibility nextChain;
    @Override
    public void writeVehicle(IVehicle vehicle) throws IOException {
        if (vehicle.getModelsSize()>3) {
            FileWriter outputStream = new FileWriter("ColumnChain");
            for (String string : vehicle.getAllModelsNames()) {
                outputStream.write(string + "\n");
            }
            outputStream.close();
        } else {
            nextChain.writeVehicle(vehicle);
        }
    }

    @Override
    public void setNextChain(ChainOfResponsibility nextChain) {
        this.nextChain=nextChain;
    }
}
    