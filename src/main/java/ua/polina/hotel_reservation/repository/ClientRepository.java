package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsClientByPassport(String passport);
}
