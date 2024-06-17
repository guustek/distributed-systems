package rsi.project.rest.repositories;

import java.util.Collection;
import java.util.Optional;

import rsi.project.rest.models.Reservation;

public interface ReservationRepository {

    Optional<Reservation> getById(int id);

    Collection<Reservation> getAll();

    Collection<Reservation> getAllByCarId(int carId);

    int add(Reservation reservation);

    boolean remove(int reservationId);
}
