package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    static List<Car> cars = new ArrayList<Car>();

    static void buildList() {
        cars.add(new Car(123, "bmw", 3594.9));
        cars.add(new Car(123, "bmw", 38346.9));
        cars.add(new Car(135, "ferrari", 130000.4));
        System.out.println(cars);
    }
    public static void main( String[] args )
    {
        buildList();
    }
}
