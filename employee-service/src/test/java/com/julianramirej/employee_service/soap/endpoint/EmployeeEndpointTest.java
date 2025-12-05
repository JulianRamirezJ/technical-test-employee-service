package com.julianramirej.employee_service.soap.endpoint;

import com.julianramirej.employee_service.soap.dto.EmployeeRequest;
import com.julianramirej.employee_service.soap.dto.EmployeeResponse;
import com.julianramirej.employee_service.soap.repository.EmployeeRepository;
import com.julianramirej.employee_service.soap.entity.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeEndpointTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeEndpoint endpoint;

    private EmployeeRequest request;

    @BeforeEach
    void setUp() {
        request = new EmployeeRequest();
        request.setNombres("Julian");
        request.setApellidos("Ramirez");
        request.setTipoDocumento("CC");
        request.setNumeroDocumento("123456");
        request.setFechaNacimiento("2000-08-11");
        request.setFechaVinculacion("2022-10-15");
        request.setCargo("Ingeniero");
        request.setSalario(3000.0);
    }

    @Test
    void shouldReturnSuccessWhenSaveSucceeds() {
        when(repository.save(any(EmployeeEntity.class))).thenReturn(new EmployeeEntity());
        EmployeeResponse response = endpoint.saveEmployee(request);

        verify(repository, times(1)).save(any());
        assertTrue(response.isSuccess());
    }

    @Test
    void shouldReturnErrorWhenSaveFails() {
        doThrow(new RuntimeException("DB error")).when(repository).save(any());

        EmployeeResponse response = endpoint.saveEmployee(request);

        verify(repository, times(1)).save(any());
        assertFalse(response.isSuccess());
    }
}