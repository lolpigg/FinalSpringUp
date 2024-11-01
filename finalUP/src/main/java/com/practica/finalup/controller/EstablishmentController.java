package com.practica.finalup.controller;

import com.practica.finalup.model.Establishment;
import com.practica.finalup.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/establishments")
@PreAuthorize("hasRole('ADMIN')")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public String list(Model model) {
        List<Establishment> establishments = establishmentService.getAllEstablishments();
        model.addAttribute("establishments", establishments);
        return "establishment/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("establishment", new Establishment());
        return "establishment/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Establishment establishment) {
        establishmentService.createEstablishment(establishment);
        return "redirect:/establishments";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Establishment establishment = establishmentService.getEstablishmentById(id).orElseThrow();
        model.addAttribute("establishment", establishment);
        return "establishment/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Establishment establishment) {
        establishment.setEstablishmentId(id);
        establishmentService.createEstablishment(establishment);
        return "redirect:/establishments";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        establishmentService.deleteEstablishment(id);
        return "redirect:/establishments";
    }
}
