package org.example.resource;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.entities.Oficina;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.repositories.OficinaRepo;
import org.example.infrastructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("oficinas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OficinaResource {

    private OficinaRepo oficinaRepo;
    private Connection connection;

    public OficinaResource() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.oficinaRepo = new OficinaRepo(connection);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao conectar ao banco de dados.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response cadastrar(@Valid Oficina oficina, @Context UriInfo uriInfo) {
        try {
            oficinaRepo.cadastrar(oficina);

            UriBuilder uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(oficina.getId()));
            return Response.created(uri.build())
                    .entity(oficina)
                    .build();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao cadastrar a oficina.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("{id}")
    public Oficina buscar(@PathParam("id") Integer id) {
        try {
            return oficinaRepo.pesquisarPorId(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Oficina não encontrada.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao buscar a oficina.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    public List<Oficina> listar() {
        try {
            return oficinaRepo.listar();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao listar as oficinas.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@Valid Oficina oficina, @PathParam("id") int id) {
        try {
            oficina.setId(id);
            oficinaRepo.atualizar(oficina);
            return Response.ok().entity(oficina).build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Oficina não encontrada.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao atualizar a oficina.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void remover(@PathParam("id") int id) {
        try {
            oficinaRepo.remover(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Oficina não encontrada.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao remover a oficina.", Response.Status.INTERNAL_SERVER_ERROR);
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
