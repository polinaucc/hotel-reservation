package ua.polina.hotel_reservation.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.polina.hotel_reservation.entity.*;
import ua.polina.hotel_reservation.repository.ReservationRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ReservationServiceTest {
    Request request;
    Room room;
    @Mock
    ReservationRepository reservationRepository;
    Reservation reservation;
    @InjectMocks
    ReservationService reservationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Description description = Description.builder()
                .id(1L)
                .roomType(RoomType.BUSINESS)
                .countOfPersons(2)
                .countOfBeds(1)
                .costPerNight(new BigDecimal(1500))
                .build();

        request = Request.builder()
                .id(1L)
                .description(description)
                .client(new Client())
                .checkInDate(LocalDate.of(2020, 7, 5))
                .checkOutDate(LocalDate.of(2020, 7, 9))
                .status(Status.New_request)
                .build();

        room = Room.builder()
                .id(1L)
                .description(description)
                .roomNumber("11r")
                .build();

        reservation = Reservation.builder()
                .id(1L)
                .request(request)
                .room(room)
                .sum(new BigDecimal(4500))
                .build();
    }

    @Test
    public void saveReservation() {
        reservationService.saveReservation(request, room);
        verify(reservationRepository, times(1)).save(Mockito.any(Reservation.class));
    }

    @Test
    public void getReservationByRequest() {
        Optional<Reservation> expectedReservation = Optional.of(reservation);
        when(reservationRepository.findByRequest(request)).thenReturn(expectedReservation);
        Optional<Reservation> actualReservation = reservationService.getReservationByRequest(request);
        Assert.assertEquals(expectedReservation, actualReservation);
    }

    @Test
    public void getAllReservations() {
        List<Reservation> expectedList = Collections.singletonList(reservation);
        when(reservationRepository.findAll()).thenReturn(expectedList);
        List<Reservation> actualList = reservationService.getAllReservations();
        Assert.assertEquals(expectedList, actualList);
    }
}