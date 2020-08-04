package com.ibabanking.client.controller;

import com.ibabanking.client.dto.ClientHistoryEntity;
import com.ibabanking.client.dto.ClientWithAllHistory;
import com.ibabanking.client.entity.ClientEntity;
import com.ibabanking.client.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientInfoController {

    private final ClientService clientService;

    public ClientInfoController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<ClientEntity>> getClient() {
        return clientService.getAll();
    }

    @GetMapping("/clientHistory/{id}")
    public ResponseEntity<List<ClientHistoryEntity>> getClientHistory(@PathVariable Long id) {
        return clientService.getHistory(id);
    }

    @GetMapping("/clientswithlatepayment/{latedays}")
    public ResponseEntity<List<ClientWithAllHistory>> getClientDetail(@PathVariable Long latedays) {
        return clientService.getClientDetail(latedays);
    }
}
