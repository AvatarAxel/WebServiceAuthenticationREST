/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.dao.EmployeeDAO;
import model.pojos.Employee;
import model.pojos.ResponseService;
import model.pojos.SesionToken;
import security.AuthorizationTokenJsonWebToken;

/**
 * REST Web Service
 *
 * @author michi
 */
@Path("basic/access")
public class accessWebServices {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of accessWebServices
     */
    public accessWebServices() {
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseService login(
            @FormParam("email") String email,
            @FormParam("password") String password
    ) {
        ResponseService responseService = new ResponseService();
        if (email == null || email.trim().isEmpty()) {
            responseService.setError(true);
            responseService.setMessage("The email is a required data...");
            return responseService;
        } else if (password == null || password.trim().isEmpty()) {
            responseService.setError(true);
            responseService.setMessage("The password is a required data...");
            return responseService;
        }
        Employee employee = EmployeeDAO.login(email, password);
        if (employee == null) {
            responseService.setError(true);
            responseService.setMessage("No employee with those credentials was found....");
            return responseService;
        }
        SesionToken sesionToken = new SesionToken();
        sesionToken.setId(employee.getIdEmployee());
        sesionToken.setName(employee.getName());
        sesionToken.setEmail(employee.getEmail());
        sesionToken = AuthorizationTokenJsonWebToken.generateToken(sesionToken);
        if (sesionToken == null || sesionToken.getTokenacceso() == null || sesionToken.getTokenacceso().isEmpty()) {
            responseService.setError(true);
            responseService.setMessage("Unable to generate the access token...");
        } else {
            responseService.setError(false);
            responseService.setMessage("Welcome: " + sesionToken.getName());
            responseService.setSesionToken(sesionToken);
        }
        return responseService;
    }

    @POST
    @Path("validatetoken")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseService validateToken(
            @FormParam("token") String token) 
    {
        ResponseService responseService = new ResponseService();
        if (token == null || token.trim().isEmpty()) {
            responseService.setError(true);
            responseService.setMessage("The token is a required data...");
            return responseService;
        }
        responseService = AuthorizationTokenJsonWebToken.validateToken(token);
        return responseService;
    }

}
