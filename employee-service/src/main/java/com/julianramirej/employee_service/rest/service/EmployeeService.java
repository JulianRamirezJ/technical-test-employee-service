package com.julianramirej.employee_service.rest.service;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public EmployeeResponse processEmployee(EmployeeRequest request) {
        return new EmployeeResponse();
    }
}