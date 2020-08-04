package com.ibabanking.clienthistory.controller;

import com.ibabanking.clienthistory.entity.ClientHistoryEntity;
import com.ibabanking.clienthistory.service.ClientHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientHistoryController {

    private final ClientHistoryService clientHistoryService;

    public ClientHistoryController(ClientHistoryService clientHistoryService) {
        this.clientHistoryService = clientHistoryService;
    }

    @GetMapping("/gethistorybyid/{id}")
    public ResponseEntity<List<ClientHistoryEntity>> getHistoryByClientId(@PathVariable Long id) {
        return clientHistoryService.getAllHistoryById(id);
    }
}
