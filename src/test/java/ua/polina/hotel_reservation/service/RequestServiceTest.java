package ua.polina.hotel_reservation.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.*;
import ua.polina.hotel_reservation.repository.RequestRepository;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RequestServiceTest {
    Request request;
    @Mock
    RequestDto requestDto;
    Client client;
    Description description;
    @Mock
    RequestRepository requestRepository;
    @InjectMocks
    RequestService requestService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        description = Description.builder()
                .id(1L)
                .roomType(RoomType.BUSINESS)
                .countOfPersons(2)
                .countOfBeds(1)
                .costPerNight(new BigDecimal(1500))
                .build();

        client = Client.builder()
                .id(1L)
                .firstNameUk("Антон")
                .firstName("Anton")
                .middleName("Volodymyrovych")
                .middleNameUk("Володимирович")
                .lastName("Povarov")
                .lastNameUk("Поваров")
                .passport("АБ456894")
                .birthday(LocalDate.of(1999, 4, 18))
                .user(new User())
                .build();

        request = Request.builder()
                .id(1L)
                .description(description)
                .client(client)
                .checkInDate(LocalDate.of(2020, 7, 5))
                .checkOutDate(LocalDate.of(2020, 7, 9))
                .status(Status.New_request)
                .build();
    }

    @Test
    public void saveNewRequest() {
        requestService.saveNewRequest(requestDto, client, description);
        verify(requestRepository, Mockito.times(1)).save(Mockito.any(Request.class));
    }

    @Test
    public void getAllRequests() {
        Page<Request> expectedList = new PageImpl<Request>(Collections.singletonList(request));
        PageRequest pageRequest = PageRequest.of(1, 2);
        when(requestRepository.findAll(pageRequest)).thenReturn(expectedList);
        Page<Request> actualList = requestService.getAllRequests(pageRequest);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void getRequestById() {
        Optional<Request> expectedRequest = Optional.of(request);
        when(requestRepository.findById(1L)).thenReturn(expectedRequest);
        Optional<Request> actualRequest = requestService.getRequestById(1L);
        Assert.assertEquals(expectedRequest, actualRequest);
    }

    @Test
    public void update() {
        requestService.update(request, Status.Accepted);
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    public void getRequestsByClient() {
        List<Request> expectedList = Collections.singletonList(request);
        when(requestRepository.findRequestByClient(client)).thenReturn(expectedList);
        List<Request> actualList = requestService.getRequestsByClient(client);
        Assert.assertEquals(expectedList, actualList);
    }
}