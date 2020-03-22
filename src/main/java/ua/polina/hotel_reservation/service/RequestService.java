package ua.polina.hotel_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.Request;
import ua.polina.hotel_reservation.entity.Status;
import ua.polina.hotel_reservation.repository.RequestRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RequestService {
    RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request saveNewRequest(RequestDto requestDto, Client client, Description description) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Request request = Request.builder()
                .client(client)
                .description(description)
                .checkInDate(LocalDate.parse(requestDto.getCheckInDate(), formatter))
                .checkOutDate(LocalDate.parse(requestDto.getCheckInDate(), formatter))
                .status(Status.New_request)
                .build();
        return  requestRepository.save(request);
    }
}
