package com.practica.finalup.controller;

import com.practica.finalup.model.Dish;
import com.practica.finalup.service.DishService;
import com.practica.finalup.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private MenuService menuService;

    @GetMapping
    public String list(Model model) {
        List<Dish> dishes = dishService.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "dish/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("menus", menuService.getAllMenus());
        return "dish/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Dish dish) {
        dishService.createDish(dish);
        return "redirect:/dishes";
    }
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.getDishById(id).orElseThrow();
        model.addAttribute("dish", dish);
        model.addAttribute("menus", menuService.getAllMenus());
        return "dish/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Dish dish) {
        dish.setDishId(id);
        dishService.createDish(dish);
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/dishes";
    }
}
