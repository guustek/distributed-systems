package rsi.soap.project.repositories;

import java.util.Collection;

import rsi.soap.project.models.Car;

public interface CarRepository {

    Car getById(int id);

    Collection<Car> getAll();
}
