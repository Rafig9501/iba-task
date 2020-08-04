package com.ibabanking.employee.proxy;

import dto.ClientWithAllHistory;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("client-service")
@RibbonClient(name = "client-history-service")
public interface ClientDetails {

    @GetMapping("/clientswithlatepayment/{latedays}")
    public List<ClientWithAllHistory> getAll(@PathVariable Long latedays);
}
