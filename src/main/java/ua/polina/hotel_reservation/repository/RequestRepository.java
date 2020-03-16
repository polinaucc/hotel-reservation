package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
