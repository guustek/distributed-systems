package rsi.project.rest.services;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import rsi.project.rest.models.Car;
import rsi.project.rest.models.Reservation;

public interface CarRentalService {

    Collection<Car> getAllCars();

    Collection<Car> getAvailableCars(Date from, Date to);

    Optional<Car> getCarById(int id);

    Collection<Reservation> getAllReservations();

    Optional<Reservation> getReservationById(int id);

    Reservation reserveCar(int carId, Date from, Date to);

    boolean cancelReservation(int reservationId);

    byte[] generatePDFReservationConfirmation(int reservationId);
}
