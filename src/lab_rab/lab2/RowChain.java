package lab_rab.lab2;

import lab_rab.lab2.interfaces.ChainOfResponsibility;
import lab_rab.lab2.interfaces.IVehicle;

import java.io.FileWriter;
import java.io.IOException;

public class RowChain implements ChainOfResponsibility {
    private ChainOfResponsibility nextChain;
    @Override
    public void writeVehicle(IVehicle vehicle) throws IOException {
        if (vehicle.getModelsSize()<=3){
            FileWriter outputStream = new FileWriter("RowChain");
            for (String string : vehicle.getAllModelsNames()){
                outputStream.write(string + " ");
            }
            outputStream.close();
        }
    }

    @Override
    public void setNextChain(ChainOfResponsibility next_chain) {
        this.nextChain=nextChain;
    }
}
    