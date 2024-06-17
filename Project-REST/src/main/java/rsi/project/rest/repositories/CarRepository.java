package rsi.project.rest.repositories;


import java.util.Collection;
import java.util.Optional;

import rsi.project.rest.models.Car;

public interface CarRepository {

    Optional<Car> getById(int id);

    Collection<Car> getAll();
}
