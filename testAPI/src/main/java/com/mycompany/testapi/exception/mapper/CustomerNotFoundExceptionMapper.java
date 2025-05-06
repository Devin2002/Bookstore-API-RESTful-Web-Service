package com.mycompany.testapi.exception.mapper;

import com.mycompany.testapi.exception.CustomerNotFoundException;
import com.mycompany.testapi.exception.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomerNotFoundExceptionMapper implements ExceptionMapper<CustomerNotFoundException> {
    @Override
    public Response toResponse(CustomerNotFoundException exception) {
        ErrorMessage error = new ErrorMessage("404", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}
