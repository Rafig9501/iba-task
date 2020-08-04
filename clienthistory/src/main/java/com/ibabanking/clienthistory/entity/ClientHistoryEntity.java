package com.ibabanking.clienthistory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class ClientHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private Long clientId;

    @Column
    @NonNull
    private Long monthlyPayedAmount;

    @Column
    @NonNull
    private Long latePaymentsDays;


}
