package com.wellsfargo.counselor.controllers;

import com.wellsfargo.counselor.entity.Security;
import com.wellsfargo.counselor.repositories.SecurityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/securities")
public class SecurityController {

    private final SecurityRepository repository;

    public SecurityController(SecurityRepository repository) {
        this.repository = repository;
    }

    // Get all securities
    @GetMapping
    public List<Security> getAllSecurities() {
        return repository.findAll();
    }

    // Get security by ID
    @GetMapping("/{id}")
    public Security getSecurityById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Create a new security
    @PostMapping
    public Security createSecurity(@RequestBody Security security) {
        return repository.save(security);
    }

    // Update an existing security
    @PutMapping("/{id}")
    public Security updateSecurity(@PathVariable Long id, @RequestBody Security securityDetails) {
        return repository.findById(id).map(security -> {
            security.setPortfolio(securityDetails.getPortfolio());
            security.setName(securityDetails.getName());
            security.setCategory(securityDetails.getCategory());
            security.setPurchaseDate(securityDetails.getPurchaseDate());
            security.setPurchasePrice(securityDetails.getPurchasePrice());
            security.setQuantity(securityDetails.getQuantity());
            return repository.save(security);
        }).orElse(null);
    }

    // Delete a security
    @DeleteMapping("/{id}")
    public void deleteSecurity(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
