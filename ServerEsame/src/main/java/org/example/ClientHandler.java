package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Car> cars;
    private Gson gson;

    public ClientHandler(Socket clientSocket, List<Car> cars) {
        this.clientSocket = clientSocket;
        this.cars = cars;
        this.gson = new Gson();

        System.out.println(clientSocket.getInetAddress());
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while (true) {
                String cmd = in.readLine();
                System.out.println(cmd);

                if (cmd == null) {
                    break;
                }

                String result = executeCommand(cmd);
                out.println(result);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String executeCommand(String cmd) {
        if ("more_expensive".equals(cmd)) {
            Car mostExpensiveCar = null;
            for (Car car : cars) {
                if (mostExpensiveCar == null || car.getPrice() > mostExpensiveCar.getPrice()) {
                    mostExpensiveCar = car;
                }
            }
            Answer answer = new Answer(true, gson.toJson(mostExpensiveCar));
            return answer.asJSON();
        } else if ("all".equals(cmd)) {
            Answer answer = new Answer(true, gson.toJson(cars));
            return answer.asJSON();
        } else if ("all_sorted".equals(cmd)) {
            List<Car> sortedCars = sortCarsByPrice(cars);
            Answer answer = new Answer(true, gson.toJson(sortedCars));
            return answer.asJSON();
        } else {
            Answer answer = new Answer(false, "Comando non valido.");
            return answer.asJSON();
        }
    }

    private List<Car> sortCarsByPrice(List<Car> cars) {
        List<Car> sortedCars = new ArrayList<Car>(cars);
        int n = sortedCars.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (sortedCars.get(i).getPrice() > sortedCars.get(j).getPrice()) {
                    Car temp = sortedCars.get(i);
                    sortedCars.set(i, sortedCars.get(j));
                    sortedCars.set(j, temp);
                }
            }
        }
        return sortedCars;
    }
}