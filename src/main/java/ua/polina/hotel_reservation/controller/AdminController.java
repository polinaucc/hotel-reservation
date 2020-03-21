package ua.polina.hotel_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.polina.hotel_reservation.dto.DescriptionDto;
import ua.polina.hotel_reservation.service.DescriptionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    DescriptionService descriptionService;

    @Autowired
    public AdminController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @RequestMapping("/add-description")
    public String getDescriptionForm(Model model) {
        model.addAttribute("description", new DescriptionDto());

        List<Integer> counts = new ArrayList<>(
                List.of(1, 2, 3));

        model.addAttribute("counts", counts);
        return "description-form";
    }

    @PostMapping("/add-description")
    public String addDescription(@ModelAttribute("description") DescriptionDto descriptionDto, Model model,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "index";
        }
        System.out.println(descriptionDto);
        descriptionService.saveNewDescription(descriptionDto);
        return "redirect:/admin/add-description";
    }
}
