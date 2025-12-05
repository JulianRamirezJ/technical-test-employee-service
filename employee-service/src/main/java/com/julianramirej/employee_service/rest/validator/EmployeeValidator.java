package com.julianramirej.employee_service.validator;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class EmployeeValidator {

    public void validate(EmployeeRequest request) {
        validateNotEmptyFields(request);
        LocalDate birthDate = validateValidDate(request.getFechaNacimiento(), "fechaNacimiento");
        LocalDate hiringDate = validateValidDate(request.getFechaVinculacion(), "fechaVinculacion");
        validateAge(birthDate);
        validateSalary(request.getSalario());
    }

    private void validateNotEmptyFields(EmployeeRequest request) {
        if (isEmpty(request.getNombres()) ||
                isEmpty(request.getApellidos()) ||
                isEmpty(request.getTipoDocumento()) ||
                isEmpty(request.getNumeroDocumento()) ||
                isEmpty(request.getFechaNacimiento()) ||
                isEmpty(request.getFechaVinculacion()) ||
                isEmpty(request.getCargo()) ||
                request.getSalario() == null) {

            throw new IllegalArgumentException("Request inválido: Todos los campos son obligatorios");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private LocalDate validateValidDate(String date, String fieldName) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Request inválido: El campo " + fieldName + " no tiene un formato de fecha válido");
        }
    }

    private void validateAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 18) {
            throw new IllegalArgumentException("Request inválido: El empleado debe ser mayor de edad");
        }
    }

    private void validateSalary(Double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Request inválido: El salario no puede ser negativo");
        }
    }
}
