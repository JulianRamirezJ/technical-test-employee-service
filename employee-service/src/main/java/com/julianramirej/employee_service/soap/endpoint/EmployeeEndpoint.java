package com.julianramirej.employee_service.soap.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.julianramirej.employee_service.soap.dto.EmployeeRequest;
import com.julianramirej.employee_service.soap.dto.EmployeeResponse;

@Endpoint
public class EmployeeEndpoint {

    private static final String NAMESPACE_URI = "http://julianramirej.com/employee_service/soap";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EmployeeRequest")
    @ResponsePayload
    public EmployeeResponse saveEmployee(@RequestPayload EmployeeRequest request) {
        EmployeeResponse response = new EmployeeResponse();
        response.setMensaje("Empleado recibido correctamente");
        return response;
    }
}