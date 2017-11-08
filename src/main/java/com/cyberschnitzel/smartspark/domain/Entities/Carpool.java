package com.cyberschnitzel.smartspark.domain.Entities;

import java.util.Date;

public class Carpool {
    private int driver_id;
    private int passenger_id;

    public Carpool(int driver_id, int passenger_id) {
        this.driver_id = driver_id;
        this.passenger_id = passenger_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    @Override
    public String toString() {
        return "Carpool{" +
                ", driver_id=" + driver_id +
                ", passenger_id=" + passenger_id +
                '}';
    }
}
