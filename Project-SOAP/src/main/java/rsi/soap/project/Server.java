package rsi.soap.project;

import javax.xml.ws.Endpoint;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rsi.soap.project.repositories.CarRepository;
import rsi.soap.project.repositories.CarRepositoryImpl;
import rsi.soap.project.repositories.ReservationRepository;
import rsi.soap.project.repositories.ReservationRepositoryImpl;
import rsi.soap.project.services.CarRentalService;
import rsi.soap.project.services.CarRentalServiceImpl;

public class Server {

    public static final int PORT = 9999;

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";

        CarRepository carRepository = new CarRepositoryImpl();
        ReservationRepository reservationRepository = new ReservationRepositoryImpl();
        CarRentalService service = new CarRentalServiceImpl(carRepository, reservationRepository);

        String serviceName = CarRentalService.class.getSimpleName();
        String address = "http://" + host + ":" + PORT + "/" + serviceName;
        Endpoint.publish(address, service);
        System.out.println(serviceName + " service published at: " + address);
        String wsdlLocation = address + "?wsdl";
        System.out.println("Schema location: " + wsdlLocation);
    }

    public static String readFile(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeToFile(Path path, String payload) {
        try {
            Files.write(path.toAbsolutePath(), payload.getBytes());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
