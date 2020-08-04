package com.ibabanking.clienthistory.service;

import com.ibabanking.clienthistory.entity.ClientHistoryEntity;
import com.ibabanking.clienthistory.repository.ClientHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientHistoryService {

    private final ClientHistoryRepository clientHistoryRepository;

    public ClientHistoryService(ClientHistoryRepository clientHistoryRepository) {
        this.clientHistoryRepository = clientHistoryRepository;
    }

    public ResponseEntity<List<ClientHistoryEntity>> getAllHistoryById(Long id) {
        List<ClientHistoryEntity> allByClientId = clientHistoryRepository.findAllByClientId(id);
        return new ResponseEntity<>(allByClientId, HttpStatus.FOUND);
    }
}
