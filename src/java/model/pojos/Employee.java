/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojos;

/**
 *
 * @author michi
 */
public class Employee {
    private Integer idEmployee;
    private String name;
    private String lastName;
    private String cellPhoneNumber;
    private String email;
    private String address;
    private String password;

    public Employee() {
    }

    public Employee(Integer idEmployee, String name, String lastName, String cellPhoneNumber, String email, String address, String password) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" + "idEmployee=" + idEmployee + ", name=" + name + ", lastName=" + lastName + ", cellPhoneNumber=" + cellPhoneNumber + ", email=" + email + ", address=" + address + ", password=" + password + '}';
    }
            
}
