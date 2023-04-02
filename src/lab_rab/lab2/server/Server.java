package lab_rab.lab2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            while (true) {
                System.out.println("Ожидаем подключения");

                Socket socket = serverSocket.accept();
                System.out.println("Подключение прошло успешно");

                try (BufferedReader inputSocketStream = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    try (PrintWriter outputSocketStream = new PrintWriter(socket.getOutputStream())) {

                        ArrayList<Double> doubles = new ArrayList<Double>();
                        String line = inputSocketStream.readLine();
                        while (line != null && line.length() > 0) {
                            doubles.add(Double.parseDouble(line));
                            line = inputSocketStream.readLine();
                        }

                        System.out.printf("Первое число: %s%nВторое число: %s%n", doubles.get(0), doubles.get(1));
                        double mul = doubles.get(0) * doubles.get(1);
                        System.out.println("Произведение: " + mul);

                        outputSocketStream.write(String.valueOf(mul));
                        outputSocketStream.flush();

                        System.out.println("Соединение закрыто");
                    }
                }
            }
        }
    }
}
