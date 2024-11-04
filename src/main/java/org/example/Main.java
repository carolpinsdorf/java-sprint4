package org.example;

import org.example.filter.CorsFilter;
import org.example.resource.*;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";
    private static final Logger logger = LogManager.getLogger(Main.class);

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in br.com.gaid package
        final ResourceConfig rc = new ResourceConfig().packages("br.com.gaid");
        rc.register(JsonBindingFeature.class);
        rc.register(CorsFilter.class);
        rc.register(AcessoResource.class);
        rc.register(ClienteResource.class);
        rc.register(OficinaResource.class);
        rc.register(CarroResource.class);
        rc.register(AgendamentoResource.class);

        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }
        URI baseUri = URI.create("http://0.0.0.0:" + port + "/");
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        logger.info("Jersey app started with endpoints available at {}", BASE_URI);
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
    }
}