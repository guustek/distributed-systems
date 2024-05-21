package rsi.soap.project.repositories;

import java.util.Collection;

import rsi.soap.project.models.Reservation;

public interface ReservationRepository {

    Reservation getById(int id);

    Collection<Reservation> getAll();

    Collection<Reservation> getAllByCarId(int carId);

    int add(Reservation reservation);

    int remove(int reservationId);
}
