/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.pojos.Employee;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author michi
 */
public class EmployeeDAO {

    public static Employee getEmployeeTest() {
        Employee employee = null;
        try {
            Faker faker = new Faker(new Locale("es-MX"));
            employee = new Employee(
                    faker.number().numberBetween(1, 100000),
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.phoneNumber().cellPhone(),
                    faker.internet().emailAddress(),
                    faker.address().fullAddress(),
                    faker.internet().password(8, 16, true, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public static List<Employee> getEmployeesListTest(int numberOfEmployees) {
        List<Employee> employeesList = new ArrayList<>();
        try {
            Faker faker = new Faker(new Locale("es-MX"));
            for (int i = 0; i < numberOfEmployees; i++) {
                Employee employee = new Employee(
                        faker.number().numberBetween(1, 100000),
                        faker.name().firstName(),
                        faker.name().lastName(),                        
                        faker.phoneNumber().cellPhone(),
                        faker.internet().emailAddress(),
                        faker.address().fullAddress(),
                        faker.internet().password(8, 16, true, true));
                employeesList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeesList;
    }
    
    public static Employee login(String email, String password) {
        Employee employee = new Employee();
        try {
            if (password.compareToIgnoreCase("SoloEstaContrasenaEsCorrecta12345") == 0) {
                Faker faker = new Faker(new Locale("es-MX"));
                employee = new Employee(
                        faker.number().numberBetween(1, 100000),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().cellPhone(),
                        email,
                        faker.address().fullAddress(),
                        faker.internet().password(8, 16, true, true, true)
                );
            }
        } catch (Exception e) { //AutorizacionTokenJWT.java      herramienta JSON Web Tokens (JWT)
            e.printStackTrace();
        }
        return employee;
    }

}
