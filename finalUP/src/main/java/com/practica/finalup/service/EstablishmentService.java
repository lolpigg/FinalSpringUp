package com.practica.finalup.service;

import com.practica.finalup.model.*;
import com.practica.finalup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    public List<Establishment> getAllEstablishments() {
        return establishmentRepository.findAll();
    }

    public Optional<Establishment> getEstablishmentById(Long id) {
        return establishmentRepository.findById(id);
    }

    public Establishment createEstablishment(Establishment establishment) {
        return establishmentRepository.save(establishment);
    }

    public Establishment updateEstablishment(Long id, Establishment establishmentDetails) {
        Establishment establishment = establishmentRepository.findById(id).orElseThrow();
        establishment.setName(establishmentDetails.getName());
        establishment.setLocation(establishmentDetails.getLocation());
        establishment.setType(establishmentDetails.getType());
        return establishmentRepository.save(establishment);
    }

    public void deleteEstablishment(Long id) {
        establishmentRepository.deleteById(id);
    }
}
