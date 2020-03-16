package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
}
