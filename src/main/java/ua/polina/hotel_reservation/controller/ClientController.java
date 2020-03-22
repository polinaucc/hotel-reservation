package ua.polina.hotel_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.polina.hotel_reservation.dto.RequestDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.entity.CurrentUser;
import ua.polina.hotel_reservation.entity.Description;
import ua.polina.hotel_reservation.entity.User;
import ua.polina.hotel_reservation.service.ClientService;
import ua.polina.hotel_reservation.service.DescriptionService;
import ua.polina.hotel_reservation.service.RequestService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    ClientService clientService;
    DescriptionService descriptionService;
    RequestService requestService;

    @Autowired
    public ClientController(ClientService clientService,
                            DescriptionService descriptionService,
                            RequestService requestService) {
        this.clientService = clientService;
        this.descriptionService = descriptionService;
        this.requestService = requestService;
    }

    @GetMapping("/apply")
    public String getRequestForm(Model model) {
        model.addAttribute("request", new RequestDto());
        model.addAttribute("error", null);

        List<Integer> counts = new ArrayList<>(
                List.of(1, 2, 3));

        model.addAttribute("counts", counts);
        return "request-form";
    }

    @PostMapping("/apply")
    public String addRequest(@ModelAttribute RequestDto requestDto, Model model,
                             BindingResult bindingResult, @CurrentUser User user) {
        System.out.println("--------------HERE---------------");
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "register-client";
        }
        try {
            Client client = clientService.getClientByUser(user)
                    .orElseThrow(() -> new IllegalArgumentException("No client"));
            Description description = descriptionService.getDescriptionByParameters(requestDto)
                    .orElseThrow(() -> new IllegalArgumentException("No rooms with such parameters"));
            requestService.saveNewRequest(requestDto, client, description);
            return "index";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            return "redirect:/client/apply";
        }
    }
}
