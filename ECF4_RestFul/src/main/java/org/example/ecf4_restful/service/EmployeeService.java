package org.example.ecf4_restful.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.ecf4_restful.dao.EmployeeRepository;
import org.example.ecf4_restful.entity.Employee;

@Path("/employees")
public class EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstname(employeeDTO.getFirstname());
        employee.setLastname(employeeDTO.getLastname());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDepartment(departmentRepository.findById(employeeDTO.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department not found")));
        employee.setJobPosition(jobPositionRepository.findById(employeeDTO.getJobPositionId()).orElseThrow(() -> new NotFoundException("JobPosition not found")));
        employeeRepository.save(employee);
    }
}

