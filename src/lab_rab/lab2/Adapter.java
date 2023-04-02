package lab_rab.lab2;

import java.io.*;

public class Adapter {
    public void writeOutputStream(String...strings) throws IOException {
        OutputStream outputStream = new FileOutputStream("Adapter.txt");
        try {
            for (String str : strings) {
                str += " ";
                byte[] buffer = str.getBytes();
                outputStream.write(buffer);
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
    