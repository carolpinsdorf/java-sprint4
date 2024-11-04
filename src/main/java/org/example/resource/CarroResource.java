package org.example.resource;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.example.entities.Carro;
import org.example.exception.EntidadeNaoEncontradaException;
import org.example.repositories.CarroRepo;
import org.example.infrastructure.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Path("carros")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarroResource {

    private CarroRepo carroRepo;
    private Connection connection;

    public CarroResource() {
        try {
            this.connection = ConnectionFactory.getConnection();
            this.carroRepo = new CarroRepo(connection);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao conectar ao banco de dados.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    public Response cadastrar(@Valid Carro carro, @Context UriInfo uriInfo) {
        try {
            carroRepo.cadastrar(carro);

            UriBuilder uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(carro.getId()));
            return Response.created(uri.build())
                    .entity(carro)
                    .build();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao cadastrar o carro.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("{id}")
    public Carro buscar(@PathParam("id") Integer id) {
        try {
            return carroRepo.pesquisarPorId(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Carro não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao buscar o carro.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    public List<Carro> listar() {
        try {
            return carroRepo.listar();
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao listar os carros.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@Valid Carro carro, @PathParam("id") int id) {
        try {
            carro.setId(id);
            carroRepo.atualizar(carro);
            return Response.ok().entity(carro).build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Carro não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao atualizar o carro.", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void remover(@PathParam("id") int id) {
        try {
            System.out.println("Tentando remover carro com ID: " + id); // Log
            carroRepo.remover(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new WebApplicationException("Carro não encontrado.", Response.Status.NOT_FOUND);
        } catch (SQLException e) {
            throw new WebApplicationException("Erro ao remover o carro: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
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
