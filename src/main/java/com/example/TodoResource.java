package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.xml.bind.JAXBElement;

@Path("todo")
public class TodoResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;

    public TodoResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Todo getTodo() {
        Todo todo = TodoDAO.instance.getModel().get(id);
        if (todo == null)
            throw new RuntimeException("Get:  Todo  with  " + id + "  not  found");
        return todo;
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public Todo getTodoHTML() {
        Todo todo = TodoDAO.instance.getModel().get(id);
        if (todo == null)
            throw new RuntimeException("Get:  Todo  with  " + id + "  not  found");
        return todo;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putTodo(JAXBElement<Todo> todo) {
        Todo c = todo.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteTodo() {
        Todo c = TodoDAO.instance.getModel().remove(id);
        if (c == null)
            throw new RuntimeException("Delete:  Todo  with  " + id + "  not  found");
    }

    private Response putAndGetResponse(Todo todo) {
        Response res;
        if (TodoDAO.instance.getModel().containsKey(todo.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        TodoDAO.instance.getModel().put(todo.getId(), todo);
        return res;
    }
}
