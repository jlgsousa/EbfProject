package com.homemade.dao;

import com.homemade.entity.Employee;

import java.util.Collection;

public interface EmployeeDao {

    void createEmployee(Employee employee);

    Collection<Employee> getEmployees();

    Employee getEmployee(int id);

    void updateEmployee(Employee employee) throws Exception;

    void deleteEmployee(int id);

}
