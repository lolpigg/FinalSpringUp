package com.practica.finalup.controller;

import com.practica.finalup.model.Visitor;
import com.practica.finalup.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public String list(Model model) {
        List<Visitor> visitors = visitorService.getAllVisitors();
        model.addAttribute("visitors", visitors);
        return "visitor/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "visitor/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Visitor visitor) {
        visitorService.createVisitor(visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Visitor visitor = visitorService.getVisitorById(id).orElseThrow();
        model.addAttribute("visitor", visitor);
        return "visitor/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Visitor visitor) {
        visitor.setVisitorId(id);
        visitorService.updateVisitor(id, visitor);
        return "redirect:/visitors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return "redirect:/visitors";
    }
}
