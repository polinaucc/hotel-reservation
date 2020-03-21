package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.RoomType;

import java.util.Optional;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
    Optional<Description> findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
            RoomType type, int countOfPersons, int countOfBeds);
}
