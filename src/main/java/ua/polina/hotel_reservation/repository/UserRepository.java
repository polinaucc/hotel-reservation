package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
