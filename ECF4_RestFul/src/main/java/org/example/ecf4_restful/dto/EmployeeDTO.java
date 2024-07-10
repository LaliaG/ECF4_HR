package org.example.ecf4_restful.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @Size(max= 255)
    private String firstname;
    private String lastname;
    private String email;
    private Long departmentId;
    private Long jobPositionId;
}
