package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
