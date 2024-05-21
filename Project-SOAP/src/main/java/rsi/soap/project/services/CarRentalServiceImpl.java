package rsi.soap.project.services;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;

import rsi.soap.project.models.Car;
import rsi.soap.project.models.Reservation;
import rsi.soap.project.repositories.CarRepository;
import rsi.soap.project.repositories.CarRepositoryImpl;
import rsi.soap.project.repositories.ReservationRepository;
import rsi.soap.project.repositories.ReservationRepositoryImpl;

@MTOM
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@WebService(endpointInterface = "rsi.soap.project.services.CarRentalService", serviceName = "CarRentalServiceImpl")
public class CarRentalServiceImpl implements CarRentalService {

    private CarRepository carRepository;
    private ReservationRepository reservationRepository;

    public CarRentalServiceImpl() {
        this.carRepository = new CarRepositoryImpl();
        this.reservationRepository = new ReservationRepositoryImpl();
    }

    public CarRentalServiceImpl(CarRepository carRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Collection<Car> getAllCars() {
        return carRepository.getAll().stream()
                .sorted(Comparator.comparingInt(Car::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Collection<Car> getAvailableCars(Date from, Date to) {
        return carRepository.getAll().stream()
                .filter(car -> isCarAvailable(car, from, to))
                .sorted(Comparator.comparingInt(Car::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private boolean isCarAvailable(Car car, Date from, Date to) {
        Collection<Reservation> reservationsByCar = reservationRepository.getAllByCarId(car.getId());
        return reservationsByCar.stream()
                .noneMatch(reservation -> !reservation.getStartDate().after(to) && !reservation.getEndDate().before(from));
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.getById(id);
    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return reservationRepository.getAll();
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationRepository.getById(id);
    }

    @Override
    public int reserveCar(int carId, Date from, Date to) {
        Car car = carRepository.getById(carId);
        Reservation reservation = new Reservation(car.getId(), from, to);
        return reservationRepository.add(reservation);
    }

    @Override
    public int cancelReservation(int reservationId) {
        return reservationRepository.remove(reservationId);
    }

    @Override
    public byte[] generatePDFReservationConfirmation(int reservationId) {
        Reservation reservation = reservationRepository.getById(reservationId);
        Car car = carRepository.getById(reservation.getCarId());
        return generatePDF(reservation, car);
    }

    private static byte[] generatePDF(Reservation reservation, Car car) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        try (Document document = new Document(pdfDocument)) {
            Paragraph title = new Paragraph("Reservation Confirmation - " + reservation.getId())
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(20);
            document.add(title);
            document.add(new Paragraph());
            document.add(new Paragraph("Start date: " + reservation.getStartDate()));
            document.add(new Paragraph("End date: " + reservation.getEndDate()));

            document.add(new Paragraph());
            document.add(new LineSeparator(new DashedLine()));
            document.add(new Paragraph());

            Paragraph carInfoTitle = new Paragraph("Car Information:")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(16);
            document.add(carInfoTitle);
            document.add(new Paragraph("Model: " + car.getModel()));
            document.add(new Paragraph("Car Type: " + car.getCarType()));
            document.add(new Paragraph("Transmission Type: " + car.getTransmissionType()));

            try {
                ImageData imageData = ImageDataFactory.create(car.getImage(), null);
                Image image = new Image(imageData);
                image.setHorizontalAlignment(HorizontalAlignment.CENTER);
                document.add(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return outputStream.toByteArray();
    }
}
