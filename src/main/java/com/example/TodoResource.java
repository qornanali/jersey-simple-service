package com.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("todo")
public class TodoResource {
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Todo getXML() {
        Todo todo = new Todo();
        todo.setSummary("This is my first todo");
        todo.setDescription("This is my first todo");
        return todo;
    }

    @GET
    @Produces({MediaType.TEXT_XML})
    public Todo getHTML() {
        Todo todo = new Todo();
        todo.setSummary("This is my first todo");
        todo.setDescription("This is my first todo");
        return todo;
    }

}
