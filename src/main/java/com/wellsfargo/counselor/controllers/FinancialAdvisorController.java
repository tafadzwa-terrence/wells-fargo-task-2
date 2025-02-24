package com.wellsfargo.counselor.controllers;

import com.wellsfargo.counselor.entity.FinancialAdvisor;
import com.wellsfargo.counselor.repositories.FinancialAdvisorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisors")
public class FinancialAdvisorController {

    private final FinancialAdvisorRepository repository;

    public FinancialAdvisorController(FinancialAdvisorRepository repository) {
        this.repository = repository;
    }

    // Get all advisors
    @GetMapping
    public List<FinancialAdvisor> getAllAdvisors() {
        return repository.findAll();
    }

    // Get advisor by ID
    @GetMapping("/{id}")
    public FinancialAdvisor getAdvisorById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Create a new advisor
    @PostMapping
    public FinancialAdvisor createAdvisor(@RequestBody FinancialAdvisor advisor) {
        return repository.save(advisor);
    }

    // Update an existing advisor
    @PutMapping("/{id}")
    public FinancialAdvisor updateAdvisor(@PathVariable Long id, @RequestBody FinancialAdvisor advisorDetails) {
        return repository.findById(id).map(advisor -> {
            advisor.setName(advisorDetails.getName());
            advisor.setEmail(advisorDetails.getEmail());
            advisor.setPhone(advisorDetails.getPhone());
            return repository.save(advisor);
        }).orElse(null);
    }

    // Delete an advisor
    @DeleteMapping("/{id}")
    public void deleteAdvisor(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
