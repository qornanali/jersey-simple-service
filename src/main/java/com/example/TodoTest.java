package com.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.client.ClientConfig;

public class TodoTest {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget target = client.target("http://localhost:8080");
        String xmlResponse = target.path("todo").request()
                .accept(MediaType.TEXT_XML).get(String.class);
        String xmlAppResponse = target.path("todo").request()
                .accept(MediaType.APPLICATION_XML).get(String.class);
        System.out.println(xmlResponse);
        System.out.println(xmlAppResponse);
    }
}
