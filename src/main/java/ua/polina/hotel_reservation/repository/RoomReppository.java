package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Room;

public interface RoomReppository extends JpaRepository<Room, Long> {
}
