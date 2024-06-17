package rsi.project.rest.repositories;

import java.util.Collection;

import rsi.project.rest.models.Reservation;

public interface ReservationRepository {

    Reservation getById(int id);

    Collection<Reservation> getAll();

    Collection<Reservation> getAllByCarId(int carId);

    int add(Reservation reservation);

    int remove(int reservationId);
}
