package com.julianramirej.employee_service.rest.service;

import com.julianramirej.employee_service.soap.client.SoapClient;
import com.julianramirej.employee_service.soap.dto.EmployeeRequestSoap;
import com.julianramirej.employee_service.rest.dto.EmployeeRequest;
import com.julianramirej.employee_service.rest.dto.EmployeeResponse;
import com.julianramirej.employee_service.validator.EmployeeValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final SoapClient soapClient;
    private final EmployeeValidator validator = new EmployeeValidator();

    public EmployeeResponse processEmployee(EmployeeRequest request) {
        try {
            validator.validate(request);
        } catch (Exception e) {
            log.warn("Validation failed: {}", e.getMessage());
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

        EmployeeRequestSoap soapReq = toSoap(request);
        boolean saved = soapClient.saveEmployee(soapReq);

        if (!saved) {
            log.error("SOAP service failed to save employee");
        }

        return response;
    }

    private String calculateAge(String fechaNacimiento) {
        LocalDate birthDate = LocalDate.parse(fechaNacimiento);
        Period age = Period.between(birthDate, LocalDate.now());
        return formatDate(age);
    }

    private String calculateVinculationTime(String fechaVinculacion) {
        LocalDate joinDate = LocalDate.parse(fechaVinculacion);
        Period vinculationTime = Period.between(joinDate, LocalDate.now());
        return formatDate(vinculationTime);
    }

    private String formatDate(Period period) {
        return period.getYears() + " años, " +
                period.getMonths() + " meses, " +
                period.getDays() + " días";
    }

    private EmployeeRequestSoap toSoap(EmployeeRequest req) {
        EmployeeRequestSoap soap = new EmployeeRequestSoap();
        soap.setNombres(req.getNombres());
        soap.setApellidos(req.getApellidos());
        soap.setTipoDocumento(req.getTipoDocumento());
        soap.setNumeroDocumento(req.getNumeroDocumento());
        soap.setFechaNacimiento(req.getFechaNacimiento());
        soap.setFechaVinculacion(req.getFechaVinculacion());
        soap.setCargo(req.getCargo());
        soap.setSalario(req.getSalario());
        return soap;
    }

}