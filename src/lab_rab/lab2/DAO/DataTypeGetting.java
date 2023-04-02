package lab_rab.lab2.DAO;

import lab_rab.lab2.interfaces.IVehicle;

import java.io.IOException;
public interface DataTypeGetting {
    void write(IVehicle vehicle);
    void read() throws IOException;
    void print();
}
