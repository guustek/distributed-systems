package rsi.project.rest;

import java.util.Collection;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsi.project.rest.models.Car;
import rsi.project.rest.models.Reservation;
import rsi.project.rest.services.CarRentalService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/car-rental")
public class CarRentalController {
    
    private final CarRentalService carRentalService;
    
    public CarRentalController(CarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }
    
    @GetMapping("/cars")
    public ResponseEntity<CollectionModel<EntityModel<Car>>> getAllCars() {
        Collection<EntityModel<Car>> cars = carRentalService.getAllCars().stream()
                .map(car -> EntityModel.of(car,
                        linkTo(methodOn(CarRentalController.class).getCarById(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarRentalController.class).getAllCars()).withRel("cars")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(cars,
                linkTo(methodOn(CarRentalController.class).getAllCars()).withSelfRel()));
    }
    
    @GetMapping("/cars/available")
    public ResponseEntity<CollectionModel<EntityModel<Car>>> getAvailableCars(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        Collection<EntityModel<Car>> cars = carRentalService.getAvailableCars(from, to).stream()
                .map(car -> EntityModel.of(car,
                        linkTo(methodOn(CarRentalController.class).getCarById(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarRentalController.class).getAvailableCars(from, to)).withRel(
                                "availableCars")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(cars,
                linkTo(methodOn(CarRentalController.class).getAvailableCars(from, to)).withSelfRel()));
    }
    
    @GetMapping("/cars/{id}")
    public EntityModel<Car> getCarById(@PathVariable int id) {
        Car car = carRentalService.getCarById(id)
                .orElseThrow(() -> new NoSuchElementException("Car with id %d not found".formatted(id)));
        return EntityModel.of(car,
                linkTo(methodOn(CarRentalController.class).getCarById(id)).withSelfRel(),
                linkTo(methodOn(CarRentalController.class).getAllCars()).withRel("cars"));
    }
    
    @GetMapping("/reservations")
    public CollectionModel<EntityModel<Reservation>> getAllReservations() {
        Collection<EntityModel<Reservation>> reservations = carRentalService.getAllReservations().stream()
                .map(reservation -> EntityModel.of(reservation,
                        linkTo(methodOn(CarRentalController.class).getReservationById(
                                reservation.getId())).withSelfRel(),
                        linkTo(methodOn(CarRentalController.class).getAllReservations()).withRel("reservations")))
                .collect(Collectors.toList());
        return CollectionModel.of(reservations,
                linkTo(methodOn(CarRentalController.class).getAllReservations()).withSelfRel());
    }
    
    @GetMapping("/reservations/{id}")
    public EntityModel<Reservation> getReservationById(@PathVariable int id) {
        Reservation reservation = carRentalService.getReservationById(id)
                .orElseThrow(() -> new NoSuchElementException("Reservation with %d not found".formatted(id)));
        return EntityModel.of(reservation,
                linkTo(methodOn(CarRentalController.class).getReservationById(id)).withSelfRel(),
                linkTo(methodOn(CarRentalController.class).getAllReservations()).withRel("reservations"));
    }
    
    @PostMapping("/reservations")
    public EntityModel<Reservation> reserveCar(
            @RequestParam int carId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        Reservation reservation = carRentalService.reserveCar(carId, from, to);
        return EntityModel.of(reservation,
                linkTo(methodOn(CarRentalController.class).getReservationById(reservation.getId())).withSelfRel(),
                linkTo(methodOn(CarRentalController.class).getAllReservations()).withRel("reservations"),
                linkTo(methodOn(CarRentalController.class).generatePDFReservationConfirmation(reservation.getId())).withRel("pdf-confirmation"));
    }
    
    
    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable int id) {
        if (!carRentalService.cancelReservation(id)) {
            throw new NoSuchElementException("Reservation with id %d does not exist".formatted(id));
        }
        return new ResponseEntity<>("Reservation with id %d canceled".formatted(id), HttpStatus.OK);
        
    }
    
    @GetMapping(value = "/reservations/{id}/confirmation", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePDFReservationConfirmation(@PathVariable int id) {
        return ResponseEntity.ok(carRentalService.generatePDFReservationConfirmation(id));
    }
}
