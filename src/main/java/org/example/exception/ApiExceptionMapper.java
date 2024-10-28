package org.example.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof EntidadeNaoEncontradaException)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }
}