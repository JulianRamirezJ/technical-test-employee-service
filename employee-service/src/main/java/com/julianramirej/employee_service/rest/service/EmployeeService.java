package com.julianramirej.employee_service.rest.service;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;
import com.julianramirej.employee_service.validator.EmployeeValidator;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeValidator validator = new EmployeeValidator();

    public EmployeeResponse processEmployee(EmployeeRequest request) {
        try {
            validator.validate(request);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        EmployeeResponse response = new EmployeeResponse();
        response.setNombres(request.getNombres());
        response.setApellidos(request.getApellidos());
        response.setTipoDocumento(request.getTipoDocumento());
        response.setNumeroDocumento(request.getNumeroDocumento());
        response.setFechaNacimiento(request.getFechaNacimiento());
        response.setFechaVinculacion(request.getFechaVinculacion());
        response.setCargo(request.getCargo());
        response.setSalario(request.getSalario());
        response.setEdadActual(calculateAge(request.getFechaNacimiento()));
        response.setTiempoVinculacion(calculateVinculationTime(request.getFechaVinculacion()));

        return response;
    }

    private String calculateAge(String fechaNacimiento) {
        LocalDate birthDate = LocalDate.parse(fechaNacimiento, DATE_FORMATTER);
        Period age = Period.between(birthDate, LocalDate.now());
        return formatDate(age);
    }

    private String calculateVinculationTime(String fechaVinculacion) {
        LocalDate joinDate = LocalDate.parse(fechaVinculacion, DATE_FORMATTER);
        Period vinculationTime = Period.between(joinDate, LocalDate.now());
        return formatDate(vinculationTime);
    }

    private String formatDate(Period period) {
        return period.getYears() + " años, " +
                period.getMonths() + " meses, " +
                period.getDays() + " días";
    }
}