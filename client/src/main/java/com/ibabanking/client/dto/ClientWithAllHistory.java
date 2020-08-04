package com.ibabanking.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ClientWithAllHistory {

    private String firstName;

    private Long monthlyPayedAmount;

    private List<Long> latePaymentsDays;

    private String lastName;

    private List<Long> listOfCredits;
}
