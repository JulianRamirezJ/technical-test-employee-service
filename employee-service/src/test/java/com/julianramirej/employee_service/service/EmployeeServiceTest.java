package com.julianramirej.employee_service.rest.service;

import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;
import com.julianramirej.employee_service.validator.EmployeeValidator;
import com.julianramirej.employee_service.soap.client.SoapClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private SoapClient soapClient;
    private EmployeeService service;
    private EmployeeRequest request;

    @BeforeEach
    void setUp() {
        service = new EmployeeService(soapClient);
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
        when(soapClient.saveEmployee(any())).thenReturn(true);

        EmployeeResponse response = service.processEmployee(request);

        verify(soapClient, times(1)).saveEmployee(any());
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

        verify(soapClient, times(0)).saveEmployee(any());
        assertTrue(ex.getMessage().contains("Todos los campos son obligatorios"));
    }
}
