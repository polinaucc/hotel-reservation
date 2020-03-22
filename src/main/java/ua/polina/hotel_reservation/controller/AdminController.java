package ua.polina.hotel_reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.polina.hotel_reservation.dto.DescriptionDto;
import ua.polina.hotel_reservation.entity.Request;
import ua.polina.hotel_reservation.service.DescriptionService;
import ua.polina.hotel_reservation.service.RequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class AdminController {
    DescriptionService descriptionService;
    RequestService requestService;

    @Autowired
    public AdminController(DescriptionService descriptionService, RequestService requestService) {
        this.descriptionService = descriptionService;
        this.requestService = requestService;
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

    @GetMapping("/requests")
    public String getRequestsPage(Model model,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Request> requestPage = requestService.getAllRequests(PageRequest.of(currentPage-1, pageSize));

        model.addAttribute("requestPage", requestPage);

        int totalPages = requestPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "requests-page";
    }
}
