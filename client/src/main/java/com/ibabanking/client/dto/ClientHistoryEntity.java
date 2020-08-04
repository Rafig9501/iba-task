package com.ibabanking.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ClientHistoryEntity {

    private Long id;

    private Long clientId;

    private Long monthlyPayedAmount;

    private Long latePaymentsDays;
}
