package ua.polina.hotel_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.Request;
import ua.polina.hotel_reservation.entity.Status;
import ua.polina.hotel_reservation.repository.RequestRepository;

@Service
public class RequestService {
    RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request saveNewRequest(RequestDto requestDto, Client client, Description description) {
        Request request = Request.builder()
                .client(client)
                .description(description)
                .checkInDate(requestDto.getCheckInDate())
                .checkOutDate(requestDto.getCheckOutDate())
                .status(Status.New_request)
                .build();
        return  requestRepository.save(request);
    }

    public Page<Request> getAllRequests(Pageable pageable){
        return requestRepository.findAll(pageable);
    }
}
