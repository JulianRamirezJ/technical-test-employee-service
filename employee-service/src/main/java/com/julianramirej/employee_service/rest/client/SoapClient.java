package com.julianramirej.employee_service.soap.client;

import com.julianramirej.employee_service.soap.dto.EmployeeRequestSoap;
import com.julianramirej.employee_service.soap.dto.EmployeeResponseSoap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
@RequiredArgsConstructor
public class SoapClient {

    private final WebServiceTemplate webServiceTemplate;
    private static final String SOAP_URI = "http://localhost:8080/ws";

    public boolean saveEmployee(EmployeeRequestSoap request) {
        EmployeeResponseSoap response =
                (EmployeeResponseSoap) webServiceTemplate.marshalSendAndReceive(SOAP_URI, request);

        return response != null && response.isSuccess();
    }
}