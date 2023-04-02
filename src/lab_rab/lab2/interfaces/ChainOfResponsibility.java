package lab_rab.lab2.interfaces;

import java.io.IOException;

public interface ChainOfResponsibility {
    void writeVehicle(IVehicle vehicle) throws IOException;
    void setNextChain(ChainOfResponsibility nextChain);
}
