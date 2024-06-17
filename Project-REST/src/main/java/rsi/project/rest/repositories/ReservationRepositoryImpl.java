package rsi.project.rest.repositories;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import rsi.project.rest.Application;
import rsi.project.rest.models.Reservation;

@Component
public class ReservationRepositoryImpl implements ReservationRepository {

    private final Path filePath = Paths.get("Project-REST/reservations.json");
    private final Map<Integer, Reservation> reservations = new HashMap<>();

    private int latestId = 0;

    public ReservationRepositoryImpl() {
        load();
    }

    @Override
    public Reservation getById(int id) {
        return reservations.get(id);
    }

    @Override
    public Collection<Reservation> getAll() {
        return reservations.values();
    }

    @Override
    public Collection<Reservation> getAllByCarId(int carId) {
        return reservations.values().stream()
                .filter(reservation -> reservation.getCarId() == carId)
                .collect(Collectors.toSet());
    }

    @Override
    public int add(Reservation reservation) {
        latestId += 1;
        reservation.setId(latestId);
        reservations.put(latestId, reservation);
        saveToFile();
        return latestId;
    }

    @Override
    public int remove(int reservationId) {
        reservations.remove(reservationId);
        saveToFile();
        return reservationId;
    }

    private void load() {
        if (!filePath.toFile().exists()) {
            try {
                filePath.toFile().createNewFile();
                saveToFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        Reservation[] savedReservations = Application.GSON.fromJson(Application.readFile(filePath), Reservation[].class);
        int i = 0;
        for (;i < savedReservations.length; i++) {
            Reservation reservation = savedReservations[i];
            reservations.put(reservation.getId(), reservation);
        }
        latestId = i;
    }

    private void saveToFile() {
        String json = Application.GSON.toJson(reservations.values());
        Application.writeToFile(filePath, json);
    }
}
