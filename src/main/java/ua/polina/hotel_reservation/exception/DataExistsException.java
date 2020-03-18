package ua.polina.hotel_reservation.exception;

import org.springframework.dao.DuplicateKeyException;

public class DataExistsException extends DuplicateKeyException {
    public DataExistsException(String msg) {
        super(msg);
    }
}
