package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.User;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsClientByPassport(String passport);

    Optional<Client> findClientByUser(User user);
}
