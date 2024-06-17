package rsi.project.rest.services;

import java.util.Collection;
import java.util.Date;

import rsi.project.rest.models.Car;
import rsi.project.rest.models.Reservation;

public interface CarRentalService {

    Collection<Car> getAllCars();

    Collection<Car> getAvailableCars(Date from, Date to);

    Car getCarById(int id);

    Collection<Reservation> getAllReservations();

    Reservation getReservationById(int id);

    Reservation reserveCar(int carId, Date from, Date to);

    int cancelReservation(int reservationId);

    byte[] generatePDFReservationConfirmation(int reservationId);
}
