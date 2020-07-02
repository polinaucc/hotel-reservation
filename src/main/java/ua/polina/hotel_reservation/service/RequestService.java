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

/**
 * The type Request service.
 *
 * @author Polina Serhiienko
 */
@Service
public class RequestService {
    /**
     * The Request repository.
     */
    RequestRepository requestRepository;

    /**
     * Class constructor, instantiates a new Request service.
     *
     * @param requestRepository the request repository
     */
    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    /**
     * Save new request.
     *
     * @param requestDto  the request dto
     * @param client      the client
     * @param description the description of the room
     * @return the request
     */
    public Request saveNewRequest(RequestDto requestDto, Client client, Description description) {
        Request request = Request.builder()
                .client(client)
                .description(description)
                .checkInDate(requestDto.getCheckInDate())
                .checkOutDate(requestDto.getCheckOutDate())
                .status(Status.New_request)
                .build();
        return requestRepository.save(request);
    }

    /**
     * Get all requests with pagination.
     *
     * @param pageable the pageable
     * @return the page
     */
    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    /**
     * Get request by id.
     *
     * @param id the id
     * @return the optional of request
     */
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    /**
     * Update request.
     *
     * @param request the request
     * @param status  new status of request
     * @return the request
     */
    public Request update(Request request, Status status) {
        request.setStatus(status);
        return requestRepository.save(request);
    }

    /**
     * Get requests by client.
     *
     * @param client the client
     * @return the request list
     */
    public List<Request> getRequestsByClient(Client client) {
        return requestRepository.findRequestByClient(client);
    }
}
