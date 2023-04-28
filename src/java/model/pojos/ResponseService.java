/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojos;

import java.util.List;

/**
 *
 * @author michi
 */
public class ResponseService {
    private boolean error;
    private String message;
    private Employee employee;
    private List<Employee> employeeList;
    private SesionToken sesionToken;

    public ResponseService() {
    }

    public ResponseService(boolean error, String message, Employee employee, List<Employee> employeeList, SesionToken sesionToken) {
        this.error = error;
        this.message = message;
        this.employee = employee;
        this.employeeList = employeeList;
        this.sesionToken = sesionToken;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public SesionToken getSesionToken() {
        return sesionToken;
    }

    public void setSesionToken(SesionToken sesionToken) {
        this.sesionToken = sesionToken;
    }
    
}
