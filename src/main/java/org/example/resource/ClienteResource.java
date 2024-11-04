package org.example.resource;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.entities.Cliente;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.repositories.ClienteRepo;
import org.example.infrastructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private ClienteRepo clienteRepo;
    private Connection connection;

    public ClienteResource() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.clienteRepo = new ClienteRepo(connection);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao conectar ao banco de dados.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response cadastrar(@Valid Cliente cliente, @Context UriInfo uriInfo) {
        try {
            clienteRepo.cadastrar(cliente);

            UriBuilder uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(cliente.getId()));
            return Response.created(uri.build())
                    .entity(cliente)
                    .build();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao cadastrar o cliente.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("{id}")
    public Cliente buscar(@PathParam("id") Integer id) {
        try {
            return clienteRepo.pesquisarPorId(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Cliente não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao buscar o cliente.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    public List<Cliente> listar() {
        try {
            return clienteRepo.listar();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao listar os clientes.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@Valid Cliente cliente, @PathParam("id") int id) {
        try {
            cliente.setId(id);
            clienteRepo.atualizar(cliente);
            return Response.ok().entity(cliente).build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Cliente não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao atualizar o cliente.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void remover(@PathParam("id") int id) {
        try {
            clienteRepo.remover(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Cliente não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao remover o cliente.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PreDestroy
    private void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
