package com.julianramirej.employee_service.rest.controller;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;
import com.julianramirej.employee_service.rest.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        return service.processEmployee(request);
    }
}
