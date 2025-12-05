package com.julianramirej.employee_service.soap.endpoint;

import com.julianramirej.employee_service.soap.dto.EmployeeRequest;
import com.julianramirej.employee_service.soap.dto.EmployeeResponse;
import com.julianramirej.employee_service.soap.entity.EmployeeEntity;
import com.julianramirej.employee_service.soap.repository.EmployeeRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://julianramirej.com/employee_service/soap";

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeEndpoint(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmployeeRequest")
    @ResponsePayload
    public EmployeeResponse saveEmployee(@RequestPayload EmployeeRequest request) {

        EmployeeResponse response = new EmployeeResponse();

        EmployeeEntity entity = new EmployeeEntity();
        entity.setNombres(request.getNombres());
        entity.setApellidos(request.getApellidos());
        entity.setTipoDocumento(request.getTipoDocumento());
        entity.setNumeroDocumento(request.getNumeroDocumento());
        entity.setFechaNacimiento(LocalDate.parse(request.getFechaNacimiento()));
        entity.setFechaVinculacion(LocalDate.parse(request.getFechaVinculacion()));
        entity.setCargo(request.getCargo());
        entity.setSalario(request.getSalario());

        try {
            EmployeeEntity saved = repository.save(entity);
            response.setSuccess(true);
            response.setMensaje("Empleado guardado correctamente con ID: " + saved.getId());
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMensaje("Error al guardar el empleado: " + e.getMessage());
        }

        return response;
    }
}