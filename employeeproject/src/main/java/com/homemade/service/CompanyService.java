package com.homemade.service;

import com.homemade.dao.CompanyDao;
import com.homemade.dao.EmployeeDao;
import com.homemade.entity.Company;
import com.homemade.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CompanyService {

    @Autowired
    @Qualifier("mockCompanyImpl")
    private CompanyDao companyDao;

    @Autowired
    private EmployeeService employeeService;

    public Collection<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    public Company getCompany(int id) {
        return companyDao.getCompany(id);
    }

    public Collection<Employee> getEmployeesFromCompany(int companyId) {
        Collection<Employee> companyEmployees = new HashSet<>();

        for (Employee e : employeeService.getEmployees()) {
            if (e.getCompanyId() == companyId) {
                companyEmployees.add(e);
            }
        }

        return companyEmployees;
    }
}
