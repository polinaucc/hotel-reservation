package ua.polina.hotel_reservation.entity;

public enum RoomType {
    ECONOMY("Economy"),
    BALCONY("Balcony"),
    BUSINESS("Business"),
    LUXURY("Luxury");

    private final String displayValue;

    RoomType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static  RoomType valueOfDisplay(String displayValue) {
        for (RoomType r : values())
            if (r.displayValue.equals(displayValue)) {
                return r;
            }
        return null;
    }
}
