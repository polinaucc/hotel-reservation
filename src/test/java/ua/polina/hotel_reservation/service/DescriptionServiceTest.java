package ua.polina.hotel_reservation.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.polina.hotel_reservation.dto.DescriptionDto;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.RoomType;
import ua.polina.hotel_reservation.repository.DescriptionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DescriptionServiceTest {
    @Mock
    DescriptionRepository descriptionRepository;
    @InjectMocks
    DescriptionService descriptionService;
    DescriptionDto descriptionDto;
    RequestDto requestDto;
    Description description;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        descriptionDto = new DescriptionDto();
        descriptionDto.setRoomType(RoomType.BUSINESS);
        descriptionDto.setCountOfPersons(2);
        descriptionDto.setCountOfBeds(1);
        descriptionDto.setCostPerNight(new BigDecimal(1500));

        requestDto = new RequestDto();
        requestDto.setRoomType(RoomType.BUSINESS);
        requestDto.setCountOfBeds(1);
        requestDto.setCountOfPerson(2);
        requestDto.setCheckInDate(LocalDate.of(2020, 7, 5));
        requestDto.setCheckOutDate(LocalDate.of(2020, 7, 9));

        description = new Description();
        description.setId(1L);
        description.setRoomType(RoomType.BUSINESS);
        description.setCountOfPersons(2);
        description.setCountOfBeds(1);
        description.setCostPerNight(new BigDecimal(1500));
    }

    @Test
    public void saveNewDescription() {
        descriptionService.saveNewDescription(descriptionDto);
        verify(descriptionRepository, times(1)).save(Mockito.any(Description.class));
    }

    @Test
    public void getDescriptionByParameters() {
        Optional<Description> expectedDescription = Optional.of(description);
        when(descriptionRepository.findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
                requestDto.getRoomType(), requestDto.getCountOfPerson(), requestDto.getCountOfBeds()))
                .thenReturn(expectedDescription);
        Optional<Description> actualDescription = descriptionService.getDescriptionByParameters(requestDto);
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void getAllDescriptions() {
        List<Description> expectedList = Collections.singletonList(description);
        when(descriptionRepository.findAll()).thenReturn(expectedList);
        List<Description> actualList = descriptionService.getAllDescriptions();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getDescriptionById() {
        Optional<Description> expectedDescription = Optional.of(description);
        when(descriptionRepository.findById(1L)).thenReturn(expectedDescription);
        Optional<Description> actualDescription = descriptionService.getDescriptionById(1L);
        assertEquals(expectedDescription, actualDescription);
    }
}