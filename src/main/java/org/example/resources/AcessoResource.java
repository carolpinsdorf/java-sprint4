package org.example.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.entities.Acesso;
import org.example.services.AcessoValidator;

import java.util.ArrayList;
import java.util.List;

@Path("/acesso")
public class AcessoResource {

    private static List<Acesso> acessos = new ArrayList<>();
    private static int idCounter = 1; // Contador de ID para novos acessos
    private AcessoValidator acessoValidator = new AcessoValidator();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAcessos() {
        return Response.ok(acessos).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAcessoById(@PathParam("id") int id) {
        Acesso acesso = acessos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (acesso == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Acesso não encontrado")
                    .build();
        }
        return Response.ok(acesso).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAcesso(Acesso newAcesso) {
        // Utilize o método correto do validador
        if (!acessoValidator.validarAcesso(newAcesso)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados de Acesso inválidos")
                    .build();
        }

        newAcesso.setId(idCounter++); // Atribui um ID único
        acessos.add(newAcesso);
        return Response.status(Response.Status.CREATED).entity(newAcesso).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAcesso(@PathParam("id") int id, Acesso updatedAcesso) {
        for (Acesso a : acessos) {
            if (a.getId() == id) {
                a.setEmailAcesso(updatedAcesso.getEmailAcesso());
                a.setUsername(updatedAcesso.getUsername());
                a.setSenha(updatedAcesso.getSenha());
                return Response.ok(a).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Acesso não encontrado").build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAcesso(@PathParam("id") int id) {
        Acesso acessoToRemove = acessos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (acessoToRemove == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Acesso não encontrado")
                    .build();
        }

        acessos.remove(acessoToRemove);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
