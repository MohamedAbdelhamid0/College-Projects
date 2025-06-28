package org.example.model;

import java.util.Objects;

public class Manager extends User {
    private String department;
    private double salary;
    public Manager() {
        super();
    }

    public Manager(Long id, String name, String email,String password ,String department,double salary) {
        super(id, name, email,password);
        this.department = department;
        this.salary = salary;
    }

    // Getter & Setter for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if(department == null){
            this.department = null;
            throw new IllegalArgumentException("Department cannot be null");
        } else if (department.equals("HR")||department.equals("Sales")||department.equals("Customerservice")) {
            this.department = department;
        }else{
            this.department = null;
            throw new IllegalArgumentException("invalid department");
        }


    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(String department) {
        if(department == null){
            this.salary = 0;
            throw new IllegalArgumentException("Salary cannot be null");
        }else if(department.equals("HR")){
            this.salary = 8000;
        } else if (department.equals("Sales")) {
            this.salary = 10000;
        }else if (department.equals("Customerservice")) {
            this.salary = 7000;
        }else{
            this.salary = 0;
            throw new IllegalArgumentException("invalid department");
        }
    }
    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manager)) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(department, manager.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
