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

import java.util.List;
import java.util.Optional;

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

    public Optional<Request> getRequestById(Long id){
        return requestRepository.findById(id);
    }

    public Request update(Request request, Status status){
        request.setStatus(status);
        return requestRepository.save(request);
    }

    public List<Request> getRequestsByClient(Client client){
        return requestRepository.findRequestByClient(client);
    }
}
