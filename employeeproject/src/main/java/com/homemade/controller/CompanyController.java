package com.homemade.controller;

import com.homemade.entity.Company;
import com.homemade.entity.Employee;
import com.homemade.service.CompanyService;
import com.homemade.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Company> getCompanies() {
        return companyService.getCompanies();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Company getCompany(@PathVariable("id") int id) {
        return companyService.getCompany(id);
    }

    @RequestMapping(value = "/{id}/avgSalary", method = RequestMethod.GET)
    public double getCompanyAverageSalary(@PathVariable("id") int id) {
        Collection<Employee> allEmployees = employeeService.getEmployees();

        Collection<Employee> companyEmployees = allEmployees.stream()
                .filter(e -> e.getCompanyId() == id)
                .collect(Collectors.toSet());

        int salarySum = 0;
        for (Employee e : companyEmployees) {
            salarySum += e.getSalary();
        }

        return salarySum * (1.0) / companyEmployees.size() * 1.0;
    }
}
