package com.homemade.entity;


import java.util.regex.Pattern;

public class Employee {
    private static Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private double salary;

    private int companyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email == null || !emailPattern.matcher(email).matches()) {
            throw new Exception("Invalid email");
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Employee: " + id + " " + firstName + " " + lastName + "\nContact: " + email + " " + address
                + "\nSalary: " + "\nCompany: " + companyId;
    }
}
