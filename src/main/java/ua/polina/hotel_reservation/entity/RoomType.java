package ua.polina.hotel_reservation.entity;

public enum RoomType {
    ECONOMY("room.type.economy"),
    BALCONY("room.type.balcony"),
    BUSINESS("room.type.business"),
    LUXURY("room.type.luxury");

    private final String displayValue;

    RoomType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
//
//    public static  RoomType valueOfDisplay(String displayValue) {
//        for (RoomType r : values())
//            if (r.displayValue.equals(displayValue)) {
//                return r;
//            }
//        return null;
//    }
}
