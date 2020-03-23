package ua.polina.hotel_reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.Room;

import java.util.List;

public interface RoomReppository extends JpaRepository<Room, Long> {
    List<Room> findRoomsByDescription(Description description);
}
