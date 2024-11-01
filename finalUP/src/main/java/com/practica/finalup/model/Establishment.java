package com.practica.finalup.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "establishments")
public class Establishment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long establishmentId;

    private String name;
    private String location;
    private String type;

    @OneToMany(mappedBy = "establishment")
    private List<Menu> menus;

    @OneToMany(mappedBy = "establishment")
    private List<Review> reviews;

    @OneToMany(mappedBy = "establishment")
    private List<Staff> staffMembers;

    @OneToMany(mappedBy = "establishment")
    private List<Order> orders;

    @OneToMany(mappedBy = "establishment")
    private List<Reservation> reservations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEstablishmentId() {
        return establishmentId;
    }

    public void setEstablishmentId(Long establishmentId) {
        this.establishmentId = establishmentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Staff> getStaffMembers() {
        return staffMembers;
    }

    public void setStaffMembers(List<Staff> staffMembers) {
        this.staffMembers = staffMembers;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }


    // Getters and Listters
}
