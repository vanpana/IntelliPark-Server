package Model;

import java.util.Date;

public class Carpool {
    private Date date;
    private int driver_id;
    private int passenger_id;

    public Carpool(Date date, int driver_id, int passenger_id) {
        this.date = date;
        this.driver_id = driver_id;
        this.passenger_id = passenger_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                "date=" + date +
                ", driver_id=" + driver_id +
                ", passenger_id=" + passenger_id +
                '}';
    }
}
