package com.ibabanking.employee.controller;

import com.sun.jersey.api.client.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @GetMapping("/getclients/{lateAmount}")
    public List<Client> getClients(@PathVariable Long lateAmount){
        return
    }
}
