package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    static int portNumber = 1234;
    static List<Car> cars = new ArrayList<Car>();

    static {
        buildList();
    }

    static void buildList() {
        cars.add(new Car(123, "bmw", 3594.9));
        cars.add(new Car(3634, "audi", 38346.9));
        cars.add(new Car(135, "ferrari", 130000.4));
        System.out.println(cars);
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, cars);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}