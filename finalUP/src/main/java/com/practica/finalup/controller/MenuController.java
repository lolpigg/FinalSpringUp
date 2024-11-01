package com.practica.finalup.controller;

import com.practica.finalup.model.Menu;
import com.practica.finalup.service.EstablishmentService;
import com.practica.finalup.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/menus")
@PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public String list(Model model) {
        List<Menu> menus = menuService.getAllMenus();
        model.addAttribute("menus", menus);
        return "menu/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        return "menu/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Menu menu) {
        menuService.createMenu(menu);
        return "redirect:/menu";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Menu menu = menuService.getMenuById(id).orElseThrow();
        model.addAttribute("menu", menu);
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        return "menu/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Menu menu) {
        menu.setMenuId(id);
        menuService.createMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return "redirect:/menus";
    }
}
