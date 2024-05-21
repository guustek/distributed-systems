package rsi.soap.project.services;


import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.Date;

import rsi.soap.project.models.Car;
import rsi.soap.project.models.Reservation;

@WebService(name = "CarRentalService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
@HandlerChain(file = "server-handler-chain.xml")
public interface CarRentalService {

    @WebMethod
    Collection<Car> getAllCars();

    @WebMethod
    Collection<Car> getAvailableCars(
            @WebParam(name = "from") Date from,
            @WebParam(name = "to") Date to);

    @WebMethod
    Car getCarById(
            @WebParam(name = "id") int id);

    @WebMethod
    Collection<Reservation> getAllReservations();

    @WebMethod
    Reservation getReservationById(
            @WebParam(name = "id") int id);

    @WebMethod
    int reserveCar(
            @WebParam(name = "carId") int carId,
            @WebParam(name = "from") Date from,
            @WebParam(name = "to") Date to);

    @WebMethod
    int cancelReservation(
            @WebParam(name = "reservationId") int reservationId);

    @WebMethod
    byte[] generatePDFReservationConfirmation(
            @WebParam(name = "reservationId") int reservationId);
}
