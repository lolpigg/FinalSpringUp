package com.practica.finalup.controller;

import com.practica.finalup.model.Order;
import com.practica.finalup.service.EstablishmentService;
import com.practica.finalup.service.OrderService;
import com.practica.finalup.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasRole('OPERATOR') or hasRole('ADMIN')")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private EstablishmentService establishmentService;
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public String list(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        return "order/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Order order = orderService.getOrderById(id).orElseThrow();
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        model.addAttribute("order", order);
        return "order/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Order order) {
        order.setOrderId(id);
        orderService.createOrder(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
