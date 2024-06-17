package rsi.project.rest.repositories;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import rsi.project.rest.models.Car;
import rsi.project.rest.models.CarType;
import rsi.project.rest.models.TransmissionType;

@Component
public class CarRepositoryImpl implements CarRepository {

    private final Map<Integer, Car> cars = new HashMap<>();

    private int latestId = 0;

    public CarRepositoryImpl() {
        load();
    }

    @Override
    public Car getById(int id) {
        return cars.get(id);
    }

    @Override
    public Collection<Car> getAll() {
        return cars.values();
    }

    private void add(Car car) {
        latestId += 1;
        car.setId(latestId);
        cars.put(latestId, car);
    }

    private void load() {
        add(new Car(0, "Dacia Sandero Stepway", CarType.SUV, TransmissionType.MANUAL, readImg("dacia_sandero.jpg")));
        add(new Car(0, "Toyota Corolla", CarType.MEDIUM, TransmissionType.AUTOMATIC, readImg("corolla.jpg")));
        add(new Car(0, "Dacia Jogger", CarType.SUV, TransmissionType.MANUAL, readImg("jogger.jpg")));
        add(new Car(0, "Ford Transit Custom", CarType.MINIVAN, TransmissionType.AUTOMATIC, readImg("ford_transit.jpg")));
        add(new Car(0, "BMW 5 Series", CarType.PREMIUM, TransmissionType.AUTOMATIC, readImg("bmw.jpg")));
    }

    private byte[] readImg(String name) {
        try {
            return getClass().getClassLoader().getResourceAsStream(name).readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
