package ua.polina.hotel_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.RoomDto;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.Room;
import ua.polina.hotel_reservation.repository.RoomReppository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    RoomReppository roomReppository;

    @Autowired
    public RoomService(RoomReppository roomReppository) {
        this.roomReppository = roomReppository;
    }

    public Room saveRoom(RoomDto roomDto, Description description) {
        Room room = Room.builder()
                .roomNumber(roomDto.getRoomNumber())
                .description(description)
                .build();
        return roomReppository.save(room);
    }

    public List<Room> getRoomsByDescription(Description description) {
        return roomReppository.findRoomsByDescription(description);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomReppository.findById(id);
    }
}
