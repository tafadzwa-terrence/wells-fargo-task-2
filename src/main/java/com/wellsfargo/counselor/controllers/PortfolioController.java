package com.wellsfargo.counselor.controllers;

import com.wellsfargo.counselor.entity.Portfolio;
import com.wellsfargo.counselor.repositories.PortfolioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioRepository repository;

    public PortfolioController(PortfolioRepository repository) {
        this.repository = repository;
    }

    // Get all portfolios
    @GetMapping
    public List<Portfolio> getAllPortfolios() {
        return repository.findAll();
    }

    // Get portfolio by ID
    @GetMapping("/{id}")
    public Portfolio getPortfolioById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Create a new portfolio
    @PostMapping
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        return repository.save(portfolio);
    }

    // Update an existing portfolio
    @PutMapping("/{id}")
    public Portfolio updatePortfolio(@PathVariable Long id, @RequestBody Portfolio portfolioDetails) {
        return repository.findById(id).map(portfolio -> {
            portfolio.setClient(portfolioDetails.getClient());
            return repository.save(portfolio);
        }).orElse(null);
    }

    // Delete a portfolio
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
