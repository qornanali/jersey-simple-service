package com.example;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

public class Tester {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget service = client.target("http://localhost:8080");
        Todo todo = new Todo("3", "Blabla");
        Response response = service.path("todos").path(todo.getId())
                .request(MediaType.APPLICATION_XML)
                .put(Entity.entity(todo, MediaType.APPLICATION_XML), Response.class);
        System.out.println(response.getStatus());
        System.out.println(service.path("todos").request().accept(MediaType.TEXT_XML).get(String.class));
        System.out.println(service.path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));
        Response checkDelete = service.path("todos/1").request().accept(MediaType.APPLICATION_XML).get();
        service.path("todos/1").request().delete();
        System.out.println(service.path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));
        Form form = new Form();
        form.param("id", "4");
        form.param("summary", "Demonstration of the client lib for forms");
        response = service.path("todos").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), Response.class);
        System.out.println("Form response " + response.getStatus());
        System.out.println(service.path("todos").request().accept(MediaType.APPLICATION_XML).get(String.class));
    }
}
