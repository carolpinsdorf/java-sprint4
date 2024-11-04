package org.example.resource;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.entities.Agendamento;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.repositories.AgendamentoRepo;
import org.example.infrastructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("agendamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendamentoResource {

    private AgendamentoRepo agendamentoRepo;
    private Connection connection;

    public AgendamentoResource() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.agendamentoRepo = new AgendamentoRepo(connection);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao conectar ao banco de dados.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response cadastrar(@Valid Agendamento agendamento, @Context UriInfo uriInfo) {
        try {
            agendamentoRepo.cadastrar(agendamento);

            UriBuilder uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(agendamento.getId()));
            return Response.created(uri.build())
                    .entity(agendamento)
                    .build();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao cadastrar o agendamento.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("{id}")
    public Response buscar(@PathParam("id") Integer id) {
        try {
            Agendamento agendamento = agendamentoRepo.pesquisarPorId(id);
            return Response.ok(agendamento).build();
        } catch (EntidadeNaoEncontradaException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento não encontrado.")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar o agendamento.")
                    .build();
        }
    }


    @GET
    public List<Agendamento> listar() {
        try {
            return agendamentoRepo.listar();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao listar os agendamentos.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@Valid Agendamento agendamento, @PathParam("id") int id) {
        try {
            agendamento.setId(id);
            agendamentoRepo.atualizar(agendamento);
            return Response.ok().entity(agendamento).build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Agendamento não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao atualizar o agendamento.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public Response remover(@PathParam("id") int id) {
        try {
            agendamentoRepo.remover(id);
            return Response.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Agendamento não encontrado.")
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao remover o agendamento.")
                    .build();
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
