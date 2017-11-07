package com.jetbrains.Model;

public class ParkingBlock {
    public enum Blocks {
        STREET,
        FREEPARKING,
        OCCUPIEDPARKING
    }

    private Blocks type;
    private int x;
    private int y;

    public ParkingBlock(int x, int y) {
        type = Blocks.STREET;
        this.x = x;
        this.y = y;
    }

    public void changeToStreet() {
        type = Blocks.STREET;
    }

    public void changeToFreeParking() {
        type = Blocks.FREEPARKING;
    }

    public void changeToOccupiedParking() {
        type = Blocks.OCCUPIEDPARKING;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Blocks getType() {
        return type;
    }
}