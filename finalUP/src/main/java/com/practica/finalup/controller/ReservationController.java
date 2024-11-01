package com.practica.finalup.controller;

import com.practica.finalup.model.Reservation;
import com.practica.finalup.service.EstablishmentService;
import com.practica.finalup.service.ReservationService;
import com.practica.finalup.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservations")
@PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private EstablishmentService establishmentService;
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public String list(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservation/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        return "reservation/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Reservation reservation) {
        reservationService.createReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id).orElseThrow();
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        model.addAttribute("reservation", reservation);
        return "reservation/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Reservation reservation) {
        reservation.setReservationId(id);
        reservationService.createReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}
