package org.example.ecf4_restful.config;

import jakarta.ws.rs.core.Application;
import org.example.ecf4_restful.resource.DepartmentResource;
import org.example.ecf4_restful.resource.EmployeeResource;
import org.example.ecf4_restful.resource.JobPositionResource;


import java.util.HashSet;
import java.util.Set;

public class ApplicationConfig extends Application {
    private Set<Object> singletons = new HashSet<>();

    public ApplicationConfig() {
        singletons.add(new EmployeeResource());
        singletons.add(new DepartmentResource());
        singletons.add(new
                JobPositionResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
