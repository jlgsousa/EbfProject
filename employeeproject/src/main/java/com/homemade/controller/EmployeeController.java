package com.homemade.controller;

import com.homemade.entity.Employee;
import com.homemade.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createEmployee(@RequestBody Employee employee) throws Exception {
        employeeService.createEmployee(employee);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Employee> getEmployees(){
        return employeeService.getEmployees();
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEmployee(@RequestBody Employee employee) throws Exception {
        employeeService.updateEmployee(employee);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Employee getEmployeeById(@PathVariable("id") int id) {
//        return employeeService.getEmployeeById(id);
//    }
}
