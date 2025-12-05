package com.julianramirej.employee_service.soap.dto;

import lombok.Data;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import java.time.LocalDate;

@Data
@XmlRootElement(name = "EmployeeRequest", namespace = "http://julianramirej.com/employee_service/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeRequestSoap {
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private String fechaNacimiento;
    private String fechaVinculacion;
    private String cargo;
    private Double salario;
}