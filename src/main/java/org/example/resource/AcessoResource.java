package org.example.resource;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.entities.Acesso;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.repository.AcessoRepo;
import org.example.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Path("acessos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AcessoResource {

    private AcessoRepo acessoRepo;
    private Connection connection;

    public AcessoResource() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.acessoRepo = new AcessoRepo(connection);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao conectar ao banco de dados.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response cadastrar(@Valid Acesso acesso, @Context UriInfo uriInfo) {
        try {
            if (acesso.getDataCadastro() == null) {
                acesso.setDataCadastro(new java.util.Date());
            }

            acessoRepo.cadastrar(acesso);

            UriBuilder uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(acesso.getId()));
            return Response.created(uri.build())
                    .entity(acesso)
                    .build();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao cadastrar o acesso.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    @GET
    @Path("{id}")
    public Acesso buscar(@PathParam("id") Integer id) {
        try {
            return acessoRepo.pesquisarPorId(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Acesso não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao buscar o acesso.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    public List<Acesso> listar() {
        try {
            return acessoRepo.listar();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao listar os acessos.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@Valid Acesso acesso, @PathParam("id") int id) {
        try {
            acesso.setId(id);
            acessoRepo.atualizar(acesso);
            return Response.ok().entity(acesso).build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Acesso não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao atualizar o acesso.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void remover(@PathParam("id") int id) {
        try {
            acessoRepo.remover(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Acesso não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao remover o acesso.", Response.Status.INTERNAL_SERVER_ERROR);
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
