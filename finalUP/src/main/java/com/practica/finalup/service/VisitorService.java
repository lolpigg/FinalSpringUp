package com.practica.finalup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.practica.finalup.model.*;
import com.practica.finalup.repository.*;
@Service
public class VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Optional<Visitor> getVisitorById(Long id) {
        return visitorRepository.findById(id);
    }

    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }
    public Visitor updateVisitor(Long id, Visitor visitorDetails) {
        Visitor visitor = visitorRepository.findById(id).orElseThrow();
        visitor.setFirstName(visitorDetails.getFirstName());
        visitor.setLastName(visitorDetails.getLastName());
        visitor.setEmail(visitorDetails.getEmail());
        visitor.setPhoneNumber(visitorDetails.getPhoneNumber());
        return visitorRepository.save(visitor);
    }

    public void deleteVisitor(Long id) {
        visitorRepository.deleteById(id);
    }
}
