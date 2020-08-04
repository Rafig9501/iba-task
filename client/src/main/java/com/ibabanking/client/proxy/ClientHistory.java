package com.ibabanking.client.proxy;

import com.ibabanking.client.dto.ClientHistoryEntity;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zuul-server")
@RibbonClient(name = "client-history-service")
public interface ClientHistory {

    @GetMapping("/client-history-service/gethistorybyid/{id}")
    List<ClientHistoryEntity> getHistory(@PathVariable("id") Long id);
}
