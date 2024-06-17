package rsi.project.rest.models;

public class Car {

    private int id;
    private String model;
    private CarType carType;
    private TransmissionType transmissionType;
    private byte[] image;

    public Car() {
    }

    public Car(int id, String model, CarType carType, TransmissionType transmissionType, byte[] image) {
        this.id = id;
        this.model = model;
        this.carType = carType;
        this.transmissionType = transmissionType;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
