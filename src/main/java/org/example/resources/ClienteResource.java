package org.example.resources;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Cliente;
import org.example.repositories.ClienteRepo;

import java.sql.Connection;
import java.util.List;

@Path("/clientes")
public class ClienteResource {

    @Inject
    private ClienteRepo clienteRepo;

    public ClienteResource(Connection connection) {
        this.clienteRepo = new ClienteRepo(connection);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> getAllClientes() {
        return clienteRepo.findAll();
    }

    @GET
    @Path("/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClienteById(@PathParam("idCliente") Long idCliente) {
        Cliente cliente = clienteRepo.findById(idCliente);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCliente(Cliente cliente) {
        clienteRepo.save(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @PUT
    @Path("/{idCliente}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente(@PathParam("idCliente") Long idCliente, Cliente cliente) {
        Cliente existingCliente = clienteRepo.findById(idCliente);
        if (existingCliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        cliente.setIdCliente(idCliente);
        clienteRepo.update(cliente);
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/{idCliente}")
    public Response deleteCliente(@PathParam("idCliente") Long idCliente) {
        Cliente cliente = clienteRepo.findById(idCliente);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        clienteRepo.delete(idCliente);
        return Response.noContent().build();
    }
}
