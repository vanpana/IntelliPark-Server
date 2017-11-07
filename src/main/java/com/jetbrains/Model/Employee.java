package com.jetbrains.Model;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String car_plate;
    private String neighbourhood;
    private Date employ_date;
    private float multiplier;
    private int parking_spot;
    private boolean is_sharing;

    public Employee(int id, String name, String surname, String email, String password, String car_plate, Date employ_date,
                    float multiplier, int parking_spot, boolean is_sharing) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.car_plate = car_plate;
        this.neighbourhood = "Centru";
        this.employ_date = employ_date;
        this.multiplier = multiplier;
        this.parking_spot = parking_spot;
        this.is_sharing = is_sharing;
    }

    public Employee(int id, String name, String surname, String email, String password, String car_plate, String neighbourhood, Date employ_date, float multiplier, int parking_spot, boolean is_sharing) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.car_plate = car_plate;
        this.neighbourhood = neighbourhood;
        this.employ_date = employ_date;
        this.multiplier = multiplier;
        this.parking_spot = parking_spot;
        this.is_sharing = is_sharing;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public Date getEmploy_date() {
        return employ_date;
    }

    public void setEmploy_date(Date employ_date) {
        this.employ_date = employ_date;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    public int getParking_spot() {
        return parking_spot;
    }

    public void setParking_spot(int parking_spot) {
        this.parking_spot = parking_spot;
    }

    public boolean isIs_sharing() {
        return is_sharing;
    }

    public void setIs_sharing(boolean is_sharing) {
        this.is_sharing = is_sharing;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", car_plate='" + car_plate + '\'' +
                ", neighbourhood='" + neighbourhood + '\'' +
                ", employ_date=" + employ_date +
                ", multiplier=" + multiplier +
                ", parking_spot=" + parking_spot +
                ", is_sharing=" + is_sharing +
                '}';
    }
}
