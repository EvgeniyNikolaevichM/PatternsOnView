package lab_rab.lab2.interfaces;

import lab_rab.lab2.Car;

import java.io.FileWriter;
import java.io.IOException;

public interface Command {
    void write_to_file (Car auto, FileWriter fileWriter) throws IOException;
}
