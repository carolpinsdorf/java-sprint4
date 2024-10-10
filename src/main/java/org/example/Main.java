package org.example;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.example.resources.AcessoResource;

import java.io.IOException;
import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig();
        rc.register(AcessoResource.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            server = startServer();
            System.out.println(String.format("Servidor iniciado em: %s", BASE_URI));
            System.in.read();
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        } finally {
            if (server != null) {
                server.shutdownNow();
                System.out.println("Servidor encerrado.");
            }
        }
    }
}
