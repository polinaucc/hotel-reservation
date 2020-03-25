package ua.polina.hotel_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.*;
import ua.polina.hotel_reservation.service.ClientService;
import ua.polina.hotel_reservation.service.DescriptionService;
import ua.polina.hotel_reservation.service.RequestService;
import ua.polina.hotel_reservation.service.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    ClientService clientService;
    DescriptionService descriptionService;
    RequestService requestService;
    ReservationService reservationService;

    @Autowired
    public ClientController(ClientService clientService,
                            DescriptionService descriptionService,
                            RequestService requestService,
                            ReservationService reservationService) {
        this.clientService = clientService;
        this.descriptionService = descriptionService;
        this.requestService = requestService;
        this.reservationService = reservationService;
    }

    @GetMapping("/apply")
    public String getRequestForm(Model model) {
        model.addAttribute("request", new RequestDto());
        model.addAttribute("error", null);

        List<Integer> counts = new ArrayList<>(
                List.of(1, 2, 3));

        model.addAttribute("counts", counts);
        return "client/request-form";
    }

    @PostMapping("/apply")
    public String addRequest(@ModelAttribute("request") RequestDto requestDto, Model model,
                             BindingResult bindingResult, @CurrentUser User user) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "register-client";
        }
        try {
            Client client = clientService.getClientByUser(user)
                    .orElseThrow(() -> new IllegalArgumentException("No client"));
            Description description = descriptionService.getDescriptionByParameters(requestDto)
                    .orElseThrow(() -> new IllegalArgumentException("No rooms with such parameters"));
            if (requestDto.getCheckOutDate().isBefore(requestDto.getCheckInDate())) {
                throw new IllegalArgumentException("Check out date must be after check in date");
            }
            requestService.saveNewRequest(requestDto, client, description);
            return "index";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "client/request-form";
        }
    }

    @GetMapping("/my-requests")
    public String getMyRequests(Model model, @CurrentUser User user,
                                @RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size) {
        Client client = clientService.getClientByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("No client"));
        List<Request> requests = requestService.getRequestsByClient(client);
        model.addAttribute("requests", requests);
        return "client/my-request";
    }

    @GetMapping("check-bill/{id}")
    public String getReservationInfo(@PathVariable("id") Long requestId,
                                     Model model) {
        Request request = requestService.getRequestById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("No such request"));
        Reservation reservation = reservationService.getReservationByRequest(request)
                .orElseThrow(() -> new IllegalArgumentException("No reservation"));
        model.addAttribute("reservation", reservation);
        return "reservation-info";
    }

    @GetMapping("/index")
    public String getIndexPage(Model model){
        return "client/client-index";
    }
}
