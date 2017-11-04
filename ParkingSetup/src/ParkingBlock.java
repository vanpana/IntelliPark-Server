class ParkingBlock {
    enum Blocks {
        STREET,
        FREEPARKING,
        OCCUPIEDPARKING
    }

    private Blocks type;
    private int x;
    private int y;

    ParkingBlock(int x, int y) {
        type = Blocks.STREET;
        this.x = x;
        this.y = y;
    }

    void changeToStreet() {
        type = Blocks.STREET;
    }

    void changeToFreeParking() {
        type = Blocks.FREEPARKING;
    }

    void changeToOccupiedParking() {
        type = Blocks.OCCUPIEDPARKING;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Blocks getType() {
        return type;
    }
}