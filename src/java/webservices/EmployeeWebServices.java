/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import java.util.List;
import model.pojos.Employee;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.dao.EmployeeDAO;
import model.pojos.ResponseService;

/**
 * REST Web Service
 *
 * @author michi
 */
@Path("token/employee")
public class EmployeeWebServices {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeWebServices
     */
    public EmployeeWebServices() {
    }

    /**
     * Retrieves representation of an instance of webservices.EmployeeWebServices
     */
    
    @GET
    @Path("searchById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseService getById(
            @PathParam("id") Integer id) {
        ResponseService responseService = new ResponseService();
        try {
            Employee employee = EmployeeDAO.getEmployeeTest();
            if (employee != null) {
                employee.setIdEmployee(id);
                responseService.setEmployee(employee);
                responseService.setError(false);
                responseService.setMessage("OK");
            } else {
                responseService.setError(true);
                responseService.setMessage("No se encontró el empleado con ese ID");
            }
        } catch (Exception e) {
            responseService.setError(true);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }

    @GET
    @Path("searchAll")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseService getAll() {
        ResponseService responseService = new ResponseService();
        try {
            List<Employee> employeeList = EmployeeDAO.getEmployeesListTest(500);
            if (employeeList != null && !employeeList.isEmpty()) {
                responseService.setEmployeeList(employeeList);
                responseService.setError(false);
                responseService.setMessage("OK");
            } else {
                responseService.setError(true);
                responseService.setMessage("No se puede consultar a los empleados...");
            }
        } catch (Exception e) {
            responseService.setError(true);
            responseService.setMessage(e.getMessage());
        }
        return responseService;
    }
    
}
