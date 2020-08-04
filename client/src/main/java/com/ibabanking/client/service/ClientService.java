package com.ibabanking.client.service;

import com.ibabanking.client.dto.ClientHistoryEntity;
import com.ibabanking.client.dto.ClientWithAllHistory;
import com.ibabanking.client.entity.ClientEntity;
import com.ibabanking.client.proxy.ClientHistory;
import com.ibabanking.client.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientHistory clientHistory;

    public ClientService(ClientRepository clientRepository, ClientHistory clientHistory) {
        this.clientRepository = clientRepository;
        this.clientHistory = clientHistory;
    }

    public ResponseEntity<List<ClientEntity>> getAll() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.FOUND);
    }

    public ResponseEntity<List<ClientHistoryEntity>> getHistory(Long id) {
        return new ResponseEntity<>(clientHistory.getHistory(id), HttpStatus.FOUND);
    }

    public ResponseEntity<List<ClientWithAllHistory>> getClientDetail(Long lateDays) {
        List<ClientEntity> allClients = getAll().getBody();
        List<ClientWithAllHistory> clients = allClients.stream().map(clientEntity -> ClientWithAllHistory.builder()
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .latePaymentsDays(getHistory(clientEntity.getId()).getBody().stream()
                        .map(ClientHistoryEntity::getLatePaymentsDays).collect(Collectors.toList()))
                .build()).collect(Collectors.toList());
        return new ResponseEntity<>(clients, HttpStatus.FOUND);
    }
}
