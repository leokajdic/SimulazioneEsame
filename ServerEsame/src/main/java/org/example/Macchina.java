package org.example;

public class Macchina {
    int id;
    String marca;
    double prezzo;

    public Macchina(int id, String marca, double prezzo) {
        this.id = id;
        this.marca = marca;
        this.prezzo = prezzo;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrezzo() {
        return prezzo;
    }

}
