package com.example.employeems.models;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private int salary;

    public Employee(int id, String firstName, String lastName, String position, String department, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPosition() {
        return position;
    }
    public String getDepartment() {
        return department;
    }
    public int getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Employee [ ID = " + id + ", Name = " + firstName + " " + lastName + ", Position = " + position +
                ", Salary = " + salary + ", Department = " + department + "]";
    }
}

