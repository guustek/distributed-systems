package rsi.project.rest.models;

import java.util.Date;

public class Reservation {
    private int id;
    private int carId;
    private Date startDate;
    private Date endDate;

    public Reservation() {
    }

    public Reservation(int carId, Date startDate, Date endDate) {
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
