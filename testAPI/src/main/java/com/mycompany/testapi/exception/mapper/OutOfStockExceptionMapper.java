package com.mycompany.testapi.exception.mapper;

import com.mycompany.testapi.exception.OutOfStockException;
import com.mycompany.testapi.exception.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {
    @Override
    public Response toResponse(OutOfStockException exception) {
        ErrorMessage error = new ErrorMessage("400", exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
