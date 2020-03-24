package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findRequestByClient(Client client);
}
