# Servicio Empleado - Prueba Técnica

## Descripción
Este proyecto implementa un servicio REST en Java (Spring Boot) que recibe los atributos de un empleado mediante el método GET,
realiza validaciones sobre algunos atributos. Específicamente se valida que estos atributos no sean vacíos, que las fechas tengan un formato válido 
y que el empleado sea mayor de edad. Además, se invoca un servicio SOAP interno que almacena la información en una base de datos MySQL.
Finalmente, el servicio responde con un JSON enriquecido con algunos datos calculados en base a los recibidos en la petición.

## Diseño 

El sistema expone un endpoint GET construido con Java Spring Boot. Este endpoint debe recibir los siguientes parámetros:
Nombres, Apellidos, Tipo de Documento, Número de Documento, Fecha de Nacimiento, Fecha de Vinculación a la Compañía, Cargo y Salario.

Después de recibir la petición, el servicio debe verificar:
 - Que ningún atributo llegue vacío.
 - Que las fechas tengan un formato válido.
 - Que el empleado sea mayor de edad (edad ≥ 18 años).

Si alguna de estas validaciones falla, el servicio debe retornar un 400 – Bad Request.

Si todas las validaciones se completan correctamente, el servicio REST debe invocar un servicio SOAP interno,
pasando el objeto Employee. Este servicio SOAP se encargará de almacenar los datos del empleado en la tabla Employee de una
base de datos MySQL.

Finalmente, una vez la información haya sido almacenada, el servicio REST retornará un JSON con:
 - Los datos originales del empleado.
 - La edad actual del empleado.
 - El tiempo de vinculación, expresado en años, meses y días.

A contiuación se presenta un diagrama con el diseño del proyecto:

![img_1.png](img_1.png)

## Planeación inicial

1. Crear estructura base del proyecto.
2. Documentar el diseño del proyecto, las herramientas a utilizar y demás consideraciones iniciales.
3. Definir el modelo `Employee`.
4. Implementar el endpoint GET.
5. Implementar las validaciones.
6. Implementar el cálculo de la edad y el tiempo de vinculación.
7. Implementar la comunicación del servicio REST con el servicio SOAP.
8. Implementar el servicio SOAP.
9. Crear la base de datos e implementar la persistencia desde el servicio SOAP.
10. Agregar pruebas unitarias.
11. Ajustes finales y documentación.

## Notas adicionales

Este repositorio se irá construyendo progresivamente siguiendo buenas prácticas de desarrollo:

- Uso de ramas `feat/type-of-change(docs,rest,soap)/brief-description` para cada componente importante.
- Pull Requests para mantener la trazabilidad del trabajo.
- Documentación general del proyecto.
