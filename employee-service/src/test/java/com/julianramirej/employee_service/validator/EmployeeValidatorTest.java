package com.julianramirej.employee_service.validator;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeValidatorTest {

    private EmployeeValidator validator;
    private EmployeeRequest request;

    @BeforeEach
    void setUp() {
        validator = new EmployeeValidator();
        request = new EmployeeRequest(
                "Julian",
                "Ramirez",
                "CC",
                "12345678",
                "2000-08-11",
                "2022-10-15",
                "Ingeniero",
                2000.0
        );
    }

    @Test
    void shouldSuccceedWhenRequestIsValid() {
        assertDoesNotThrow(() -> validator.validate(request));
    }

    @Test
    void shouldFailWhenSomeFieldIsEmpty() {
        request.setNombres("");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(request));

        assertEquals("Request inválido: Todos los campos son obligatorios", ex.getMessage());
    }

    @Test
    void shouldFailWhenDateFormatIsInvalid() {
        request.setFechaNacimiento("1990/10/05");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(request));

        assertTrue(ex.getMessage().contains("fechaNacimiento"));
    }

    @Test
    void shouldFailWhenEmployeeIsUnderage() {
        request.setFechaNacimiento("2010-01-01");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(request));

        assertEquals("Request inválido: El empleado debe ser mayor de edad", ex.getMessage());
    }

    @Test
    void shouldFailWhenSalaryIsNegative() {
        request.setSalario(-1000.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validate(request));

        assertEquals("Request inválido: El salario no puede ser negativo", ex.getMessage());
    }
}