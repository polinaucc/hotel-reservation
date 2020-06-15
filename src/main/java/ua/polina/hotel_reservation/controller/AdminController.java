package ua.polina.hotel_reservation.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.polina.hotel_reservation.dto.DescriptionDto;
import ua.polina.hotel_reservation.dto.ReservationDto;
import ua.polina.hotel_reservation.dto.RoomDto;
import ua.polina.hotel_reservation.entity.*;
import ua.polina.hotel_reservation.service.DescriptionService;
import ua.polina.hotel_reservation.service.RequestService;
import ua.polina.hotel_reservation.service.ReservationService;
import ua.polina.hotel_reservation.service.RoomService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Admin controller. This controller is used for admin functions
 */
@Controller
@SessionAttributes("request")
@RequestMapping("/admin")
public class AdminController {
    /**
     * The Description service.
     */
    DescriptionService descriptionService;
    /**
     * The Request service.
     */
    RequestService requestService;
    /**
     * The Room service.
     */
    RoomService roomService;
    /**
     * The Reservation service.
     */
    ReservationService reservationService;
    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);
    private ResourceBundle rb;

    /**
     * Constructor
     *
     * @param descriptionService the description service
     * @param requestService     the request service
     * @param roomService        the room service
     * @param reservationService the reservation service
     */
    @Autowired
    public AdminController(DescriptionService descriptionService,
                           RequestService requestService,
                           RoomService roomService,
                           ReservationService reservationService) {
        rb = ResourceBundle.getBundle("messages", new Locale("en", "UK"));
        this.descriptionService = descriptionService;
        this.requestService = requestService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }


    /**
     * Gets the page with form for adding description.
     *
     * @param model the model
     * @return the page
     */
    @RequestMapping("/add-description")
    public String getDescriptionForm(Model model) {
        model.addAttribute("description", new DescriptionDto());

        List<Integer> counts = new ArrayList<>(
                List.of(1, 2, 3));

        model.addAttribute("counts", counts);
        return "admin/description-form";
    }

    /**
     * Method is used to save description
     *
     * @param descriptionDto the description dto
     * @param model          the model
     * @param bindingResult  the binding result
     * @return the string
     */
    @PostMapping("/add-description")
    public String addDescription(@ModelAttribute("description") DescriptionDto descriptionDto, Model model,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/index";
        }
        descriptionService.saveNewDescription(descriptionDto);
        return "admin/description-form";
    }

    /**
     * Gets requests page.
     *
     * @param model the model
     * @param page  the page
     * @param size  the size
     * @return the requests page
     */
    @GetMapping("/requests")
    public String getRequestsPage(Model model,
                                  @RequestParam("page") Optional<Integer> page,
                                  @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Request> requestPage = requestService.getAllRequests(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("requestPage", requestPage);

        int totalPages = requestPage.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin/requests-page";
    }

    /**
     * Method is used to get rooms for page with find room form
     *
     * @param requestId the request id
     * @param model     the model
     * @return page
     */
    @RequestMapping("/find-room/{id}")
    public String findRoom(@PathVariable("id") Long requestId, Model model) {
        Request request = requestService.getRequestById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("No such request"));
        List<Room> rooms = roomService.getRoomsByDescription(request.getDescription());

        List<Room> wrongRooms = new ArrayList<>();

        List<Reservation> reservations = reservationService.getAllReservations();

        for (Room room : rooms) {
            findWrongRooms(request, wrongRooms, reservations, room);
        }

        for (Room r : wrongRooms) {
            rooms.remove(r);
        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("reservation", new ReservationDto());
        model.addAttribute("request", request);

        if (rooms.size() == 0) requestService.update(request, Status.Rejected);
        else requestService.update(request, Status.Accepted);

        return "admin/find-room";
    }

    /**
     * Method is used to find rooms that are booked on the dates desired by client
     *
     * @param request      the request
     * @param wrongRooms   the list that contains reserved rooms
     * @param reservations the reservations
     * @param room         current room for checking
     */
    private void findWrongRooms(Request request, List<Room> wrongRooms, List<Reservation> reservations, Room room) {
        for (Reservation res : reservations) {
            if (res.getRoom().equals(room) &&
                    ((request.getCheckInDate().compareTo(res.getRequest().getCheckInDate()) >= 0 &&
                            request.getCheckInDate().isBefore(res.getRequest().getCheckOutDate())) ||
                            (request.getCheckOutDate().compareTo(res.getRequest().getCheckInDate()) >= 0 &&
                                    request.getCheckOutDate().compareTo(res.getRequest().getCheckOutDate()) <= 0))) {
                wrongRooms.add(room);
                break;
            }
        }
    }

    /**
     * Save reservations
     *
     * @param reservationDto the reservation dto
     * @param request        the request
     * @param bindingResult  the binding result
     * @param model          the model
     * @return the page to redirect to
     */
    @PostMapping("/add-reservation")
    public String addReservation(@ModelAttribute("reservation") ReservationDto reservationDto,
                                 @ModelAttribute("request") Request request,
                                 BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        Room room = roomService.getRoomById(reservationDto.getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("No such room"));

        reservationService.saveReservation(request, room);

        return "redirect:/admin/requests";
    }

    /**
     * Added attributed and gets the page with the room form.
     *
     * @param model the model
     * @return the page
     */
    @GetMapping("/add-room")
    public String getRoomForm(Model model) {
        model.addAttribute("error", null);
        model.addAttribute("newroom", new RoomDto());
        model.addAttribute("descriptions", descriptionService.getAllDescriptions());
        return "admin/add-room-form";
    }

    /**
     * Add new room.
     *
     * @param roomDto       the room dto
     * @param bindingResult the binding result
     * @param model         the model
     * @return the page
     */
    @PostMapping("/add-room")
    public String addNewRoom(@ModelAttribute("newroom") RoomDto roomDto,
                             BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "index";
            }
            Description description = descriptionService.getDescriptionById(roomDto.getDescriptionId())
                    .orElseThrow(() -> new IllegalArgumentException("no.description"));
            roomService.saveRoom(roomDto, description);

            return "redirect:/admin/add-room";
        } catch (IllegalArgumentException ex) {
            LOGGER.error(rb.getString(ex.getMessage()));
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }

    /**
     * Gets reservation info.
     *
     * @param requestId the request id
     * @param model     the model
     * @return 5the page
     */
    @GetMapping("check-reservation/{id}")
    public String getReservationInfo(@PathVariable("id") Long requestId,
                                     Model model) {
        try {
            Request request = requestService.getRequestById(requestId)
                    .orElseThrow(() -> new IllegalArgumentException("no.request"));
            Reservation reservation = reservationService.getReservationByRequest(request)
                    .orElseThrow(() -> new IllegalArgumentException("no.reservation"));
            model.addAttribute("reservation", reservation);
            return "reservation-info";
        } catch (IllegalArgumentException ex) {
            LOGGER.error(rb.getString(ex.getMessage()));
            model.addAttribute("error", ex.getMessage());
            return "error";
        }
    }

    /**
     * Gets index page.
     *
     * @return the index page
     */
    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }
}

