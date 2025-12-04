package com.julianramirej.employee_service.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String nombres;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private String fechaNacimiento;
    private String fechaVinculacion;
    private String cargo;
    private Double salario;
    private String edadActual;
    private String tiempoVinculacion;
}
