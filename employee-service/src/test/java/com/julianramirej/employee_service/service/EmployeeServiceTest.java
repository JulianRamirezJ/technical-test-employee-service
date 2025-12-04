package com.julianramirej.employee_service.rest.service;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;
import com.julianramirej.employee_service.validator.EmployeeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService service;
    private EmployeeRequest request;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
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
    void shouldReturnValidResponse() {
        EmployeeResponse response = service.processEmployee(request);

        assertEquals(request.getNombres(), response.getNombres());
        assertEquals(request.getApellidos(), response.getApellidos());
        assertEquals(request.getCargo(), response.getCargo());
        assertEquals(request.getSalario(), response.getSalario());
        assertNotNull(response.getEdadActual());
        assertNotNull(response.getTiempoVinculacion());
    }

    @Test
    void shouldThrowExceptionWhenRequestInvalid() {
        request.setNombres("");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.processEmployee(request));
        assertTrue(ex.getMessage().contains("Todos los campos son obligatorios"));
    }
}
