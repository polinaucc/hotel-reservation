package ua.polina.hotel_reservation.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.polina.hotel_reservation.dto.SignUpDto;
import ua.polina.hotel_reservation.entity.Role;
import ua.polina.hotel_reservation.exception.DataExistsException;
import ua.polina.hotel_reservation.service.ClientService;

import javax.validation.Valid;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/auth")
public class AuthController {
    ClientService clientService;
    private static final Logger LOGGER = LogManager.getLogger(AuthController.class);
    private ResourceBundle rb;

    @Autowired
    public AuthController(ClientService clientService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "UK"));
        this.clientService = clientService;
    }

    @GetMapping("/sign-up")
    public String getRegisterPage(Model model) {
        model.addAttribute("signUp", new SignUpDto());
        model.addAttribute("error", null);
        return "register-client";
    }

    @PostMapping("/sign-up")
    public String registerClient(@Valid @ModelAttribute("signUp") SignUpDto signUpDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "register-client";
        }
        try {
            clientService.saveNewClient(signUpDto);
            return "redirect:/auth/login";
        } catch (DataExistsException ex) {
            LOGGER.error(rb.getString(Objects.requireNonNull(ex.getMessage())));
            model.addAttribute("error", ex.getMessage());
            return "register-client";
        }
    }

    @RequestMapping("/login")
    public String getLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return "login";
    }

    @RequestMapping("/default-success")
    public String getSuccessPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(Role.ADMIN)) {
            return "redirect:/admin/index";
        } else if (SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(Role.CLIENT)) {
            return "redirect:/client/index";
        }
        return "index";
    }

    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }
}
