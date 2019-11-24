package com.homemade.service;

import com.homemade.dao.EmployeeDao;
import com.homemade.entity.Company;
import com.homemade.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("mockEmployeeImpl")
    private EmployeeDao employeeDao;

    @Autowired
    private CompanyService companyService;

    public void createEmployee(Employee employee) throws Exception {
        boolean validEmployee = false;

        Collection<Company> companies = companyService.getCompanies();
        if (companies != null) {
            validEmployee = companies.stream()
                    .anyMatch(c ->  c.getCompanyId() == employee.getCompanyId());
        }

        if (!validEmployee) {
            throw new Exception("Invalid Company Id in " + employee.toString()
                    + "\n no such company");
        }

        employeeDao.createEmployee(employee);
    }

    public Collection<Employee> getEmployees() {
        return employeeDao.getEmployees();
    }

    public void updateEmployee(Employee employee) throws Exception {
        employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(int id) {
        employeeDao.deleteEmployee(id);
    }

    public Company getCompanyFromEmployee(int employeeId) {
        Company company = null;
        Employee employee = employeeDao.getEmployee(employeeId);

        if (employee != null) {
            company = companyService.getCompany(employee.getCompanyId());
        }

        return company;
    }
}
