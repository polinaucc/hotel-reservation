package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
