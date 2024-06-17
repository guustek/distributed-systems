package rsi.project.rest.repositories;


import java.util.Collection;

import rsi.project.rest.models.Car;

public interface CarRepository {

    Car getById(int id);

    Collection<Car> getAll();
}
