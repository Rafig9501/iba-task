package com.ibabanking.clienthistory.autorun;

import com.ibabanking.clienthistory.entity.ClientHistoryEntity;
import com.ibabanking.clienthistory.repository.ClientHistoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Autorun implements CommandLineRunner {

    private final ClientHistoryRepository clientHistoryRepository;

    public Autorun(ClientHistoryRepository clientHistoryRepository) {
        this.clientHistoryRepository = clientHistoryRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        clientHistoryRepository.save(new ClientHistoryEntity(1L, 1L, 1L));
    }
}
