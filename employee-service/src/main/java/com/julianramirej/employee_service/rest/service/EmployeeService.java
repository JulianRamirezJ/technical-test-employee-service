package com.julianramirej.employee_service.rest.service;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;
import com.julianramirej.employee_service.validator.EmployeeValidator;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeValidator validator = new EmployeeValidator();

    public EmployeeResponse processEmployee(EmployeeRequest request) {
        validator.validate(request);
        return new EmployeeResponse();
    }
}