package ua.polina.hotel_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.ReservationDto;
import ua.polina.hotel_reservation.entity.Request;
import ua.polina.hotel_reservation.entity.Reservation;
import ua.polina.hotel_reservation.entity.Room;
import ua.polina.hotel_reservation.repository.ReservationRepository;

import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Request request, Room room) {
        Reservation reservation = Reservation.builder()
                .request(request)
                .room(room)
                .sum(room.getDescription().getCostPerNight()
                        .multiply(new BigDecimal(DAYS.between(request.getCheckInDate(), request.getCheckOutDate()))))
                .build();
        return reservationRepository.save(reservation);
    }
}
