package ua.polina.hotel_reservation.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.polina.hotel_reservation.dto.RoomDto;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.Room;
import ua.polina.hotel_reservation.entity.RoomType;
import ua.polina.hotel_reservation.repository.RoomReppository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {
    @Mock
    RoomReppository roomReppository;

    @InjectMocks
    RoomService roomService;

    @InjectMocks
    RoomDto roomDto;

    Description description1;
    Description description2;

    List<Room> rooms;


    @Before
    public void setUp() throws Exception {
        description1 = Description.builder()
                .id(1L)
                .costPerNight(new BigDecimal(745))
                .countOfBeds(2)
                .countOfPersons(3)
                .roomType(RoomType.BALCONY)
                .build();
        description2 = Description.builder()
                .id(2L)
                .costPerNight(new BigDecimal(250))
                .countOfBeds(1)
                .countOfPersons(1)
                .roomType(RoomType.ECONOMY)
                .build();
        roomDto = RoomDto.builder()
                .roomNumber("1-10")
                .descriptionId(47L)
                .build();
        rooms = Arrays.asList(
                Room.builder()
                        .id(1L)
                        .roomNumber("1-04")
                        .description(description1)
                        .build(),
                Room.builder()
                        .id(2L)
                        .roomNumber("1-05")
                        .description(description2)
                        .build(),
                Room.builder()
                        .id(3L)
                        .roomNumber("1-06")
                        .description(description1)
                        .build()
        );
        roomService = new RoomService(roomReppository);
        MockitoAnnotations.initMocks(this);
    }

        @Test
        public void saveRoom() {
            Room room1 = roomService.saveRoom(roomDto, description1);
            when(roomReppository.save(any(Room.class))).thenReturn(room1);
            Assert.assertEquals(room1, rooms.get(0));
        }

    @Test
    public void getRoomsByDescription() {
        Room roomWithDescription2 = rooms.get(1);
        List<Room> expectedRoomsList = Arrays.asList(roomWithDescription2);

        when(roomReppository.findRoomsByDescription(description2)).thenReturn(expectedRoomsList);

        List<Room> actualRoomsList = roomService.getRoomsByDescription(description2);

        Assert.assertEquals(1, actualRoomsList.size());
        Assert.assertEquals(expectedRoomsList, actualRoomsList);
    }

    @Test
    public void getRoomById() {
        Room room = rooms.get(0);
        Long id = room.getId();

        Optional<Room> expectedRoom = Optional.of(room);

        when(roomReppository.findById(id))
                .thenReturn(expectedRoom);

        Optional<Room> room1 = roomService.getRoomById(id);

        Assert.assertEquals(expectedRoom, room1);
        verify(roomReppository, times(1)).findById(id);

    }
}