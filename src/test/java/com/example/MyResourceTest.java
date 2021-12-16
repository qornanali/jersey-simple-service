package com.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyResourceTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        server = Main.startServer();
        Client c = ClientBuilder.newClient();

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }

    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
