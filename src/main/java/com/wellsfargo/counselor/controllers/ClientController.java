package com.wellsfargo.counselor.controllers;

import com.wellsfargo.counselor.entity.Client;
import com.wellsfargo.counselor.repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    // Get all clients
    @GetMapping
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    // Get client by ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Create a new client
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return repository.save(client);
    }

    // Update an existing client
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        return repository.findById(id).map(client -> {
            client.setName(clientDetails.getName());
            client.setEmail(clientDetails.getEmail());
            client.setPhone(clientDetails.getPhone());
            client.setFinancialAdvisor(clientDetails.getFinancialAdvisor());
            return repository.save(client);
        }).orElse(null);
    }

    // Delete a client
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
