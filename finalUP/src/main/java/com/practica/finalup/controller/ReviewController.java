package com.practica.finalup.controller;

import com.practica.finalup.model.Review;
import com.practica.finalup.service.EstablishmentService;
import com.practica.finalup.service.ReviewService;
import com.practica.finalup.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private EstablishmentService establishmentService;
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('OPERATOR') or hasRole('ADMIN')")
    public String list(Model model) {
        List<Review> reviews = reviewService.getAllReviews();
        model.addAttribute("reviews", reviews);
        return "review/list";
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        return "review/create";
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/create")
    public String create(@ModelAttribute Review review) {
        reviewService.createReview(review);
        return "redirect:/reviews";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id).orElseThrow();
        model.addAttribute("establishments", establishmentService.getAllEstablishments());
        model.addAttribute("visitors", visitorService.getAllVisitors());
        model.addAttribute("review", review);
        return "review/edit";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Review review) {
        review.setReviewId(id);
        reviewService.createReview(review);
        return "redirect:/reviews";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }
}
