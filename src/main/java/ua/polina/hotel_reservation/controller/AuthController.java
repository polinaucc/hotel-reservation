package ua.polina.hotel_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.polina.hotel_reservation.dto.SignUpDto;
import ua.polina.hotel_reservation.entity.Client;
import ua.polina.hotel_reservation.service.ClientService;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    ClientService clientService;

    @Autowired
    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/sign-up")
    public String getRegisterPage(Model model) {
        System.out.println("--------------HEY_____________");
        model.addAttribute("signUp", new SignUpDto());
        return "register-client";
    }

    @PostMapping("/sign-up")
    public String registerClient(@Valid @ModelAttribute("signUp") SignUpDto signUpDto,
                                 BindingResult bindingResult,
                                 Model model) {
        System.out.println("----------------------------OK------------------------------");
        if (bindingResult.hasErrors()) {
            return "register-client";
        }
        clientService.saveNewClient(signUpDto);
        return "register-client";
    }
}
