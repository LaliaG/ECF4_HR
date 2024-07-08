package org.example.ecf4_restful.dao;

import org.example.ecf4_restful.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee> findByLastName(String lastName);
    List<Employee> findByDepartmentName(String departmentName);
}
