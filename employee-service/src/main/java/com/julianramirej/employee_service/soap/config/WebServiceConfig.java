package com.julianramirej.employee_service.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.core.io.ClassPathResource;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "employees")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema employeesSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("EmployeePort");
        definition.setLocationUri("/ws");
        definition.setTargetNamespace("http://julianramirej.com/employee_service/soap");
        definition.setSchema(employeesSchema);
        return definition;
    }

    @Bean
    public XsdSchema employeesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
    }
}