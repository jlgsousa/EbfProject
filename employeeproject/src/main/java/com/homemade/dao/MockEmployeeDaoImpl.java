package com.homemade.dao;

import com.homemade.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("mockEmployeeImpl")
public class MockEmployeeDaoImpl implements EmployeeDao{
    private static int index = 0;
    private static Set<Employee> employees;

    private static Employee createEmployee(int id, String firstName, String lastName, String email, String address,
                                     double salary, int companyId) {

        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        try {
            employee.setEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        employee.setAddress(address);
        employee.setId(id);
        employee.setSalary(salary);
        employee.setCompanyId(companyId);

        return employee;
    }

    static {
        employees = new HashSet<Employee>() {
            {
                add(createEmployee(index++,"John", "Doe", "johnDoe@gmail.com", "johnDoeStreet",
                        800, 1));
                add(createEmployee(index++,"Charles", "Button", "charlesBut@gmail.com", "johnDoeStreet",
                        500, 2));
                add(createEmployee(index++,"Tim", "Krazinski", "brunoKraz@gmail.com", "johnDoeStreet",
                         100, 3));
                add(createEmployee(index++,"Darius", "Mud", "dariusMud@gmail.com", "johnDoeStreet",
                        2000, 4));
            }
        };
    }

    @Override
    public void createEmployee(Employee employee) {
        employee.setId(index++);
        employees.add(employee);
    }

    @Override
    public Collection<Employee> getEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployee(int id) {
        return employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateEmployee(Employee employee) throws Exception {
        Employee oldEmployee = getEmployee(employee.getId());
        if (oldEmployee != null) {
            oldEmployee.setFirstName(employee.getFirstName());
            oldEmployee.setLastName(employee.getLastName());
            oldEmployee.setEmail(employee.getEmail());
            oldEmployee.setAddress(employee.getAddress());
            oldEmployee.setSalary(employee.getSalary());
        }
    }

    @Override
    public void deleteEmployee(int id) {
        employees.remove(getEmployee(id));
    }
}
