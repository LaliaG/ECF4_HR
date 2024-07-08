import org.example.ecf4_restful.dao.DepartmentRepository;
import org.example.ecf4_restful.dao.EmployeeRepository;
import org.example.ecf4_restful.dao.JobPositionRepository;
import org.example.ecf4_restful.entity.Department;
import org.example.ecf4_restful.entity.Employee;
import org.example.ecf4_restful.entity.JobPosition;
import org.example.ecf4_restful.service.EmployeeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private JobPositionRepository positionRepository;

    @Test
    public void testAddEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstname("John");
        employeeDTO.setLastname("Doe");
        employeeDTO.setEmail("john.doe@example.com");
        employeeDTO.setDepartmentId(1L);
        employeeDTO.setPositionId(1L);

        Department department = new Department();
        department.setId(1L);
        department.setName("HR");

        JobPosition jobPosition = new JobPosition();
        jobPosition.setId(1L);
        jobPosition.setTitle("Manager");

        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(positionRepository.findById(1L)).thenReturn(Optional.of(jobPosition));

        employeeService.addEmployee(employeeDTO);

        ArgumentCaptor<Employee> employeeCaptor = ArgumentCaptor.forClass(Employee.class);
        Mockito.verify(employeeRepository).save(employeeCaptor.capture());

        Employee savedEmployee = employeeCaptor.getValue();
        Assert.assertEquals("John", savedEmployee.getFirstname());
        Assert.assertEquals("Doe", savedEmployee.getLastname());
        Assert.assertEquals("john.doe@example.com", savedEmployee.getEmail());
        Assert.assertEquals(department, savedEmployee.getDepartment());
        Assert.assertEquals(jobPosition, savedEmployee.getJobPosition());
    }
}
