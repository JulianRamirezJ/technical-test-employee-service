package com.julianramirej.employee_service.soap.dto;

import lombok.Data;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@Data
@XmlRootElement(name = "EmployeeResponse", namespace = "http://julianramirej.com/employee_service/soap")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeResponse {
    private String mensaje;
}