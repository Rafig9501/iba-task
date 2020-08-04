package com.ibabanking.clienthistory.repository;

import com.ibabanking.clienthistory.entity.ClientHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientHistoryRepository extends JpaRepository<ClientHistoryEntity, Long> {

    List<ClientHistoryEntity> findAllByClientId(Long id);
}
