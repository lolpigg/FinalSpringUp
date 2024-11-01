package com.practica.finalup.controller;

import com.practica.finalup.model.Staff;
import com.practica.finalup.service.EstablishmentService;
import com.practica.finalup.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public String list(Model model) {
        List<Staff> staffMembers = staffService.getAllStaff();
        model.addAttribute("staffMembers", staffMembers);
        return "staff/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("staffMember", new Staff());
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        return "staff/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Staff staffMember) {
        staffService.createStaff(staffMember);
        return "redirect:/staff";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Staff staffMember = staffService.getStaffById(id).orElseThrow();
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("staffMember", staffMember);
        return "staff/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Staff staffMember) {
        staffMember.setStaffId(id);
        staffService.createStaff(staffMember);
        return "redirect:/staff";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}
