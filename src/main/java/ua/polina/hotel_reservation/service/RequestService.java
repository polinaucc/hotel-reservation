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
 * The type Request service. It provides processing data received
 * from controller or from repository
 *
 * @author Polina Serhiienko
 */
@Service
public class RequestService {
    /**
     * The Request repository field.
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
     * Method that saves new request.
     *
     * @param requestDto  the request dto
     * @param client      the client
     * @param description the description of the room
     * @return the request, that was saved
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
     * Method gets all requests using pagination.
     *
     * @param pageable information about page
     * @return the page with requested data
     */
    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }

    /**
     * Method gets request by id.
     *
     * @param id request id
     * @return the optional of request
     */
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    /**
     * Method updates the request.
     *
     * @param request the request
     * @param status  new status of request
     * @return request, that was updated
     */
    public Request update(Request request, Status status) {
        request.setStatus(status);
        return requestRepository.save(request);
    }

    /**
     * Method gets requests by client.
     *
     * @param client the client to search
     * @return the client request list
     */
    public List<Request> getRequestsByClient(Client client) {
        return requestRepository.findRequestByClient(client);
    }
}
