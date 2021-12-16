package com.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("todos")
public class TodosResource {

    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Todo> getTodosBrowser() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDAO.instance.getModel().values());
        return todos;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDAO.instance.getModel().values());
        return
                todos;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = TodoDAO.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newTodo(@FormParam("id") String id,
                        @FormParam("summary") String summary, @FormParam("description")
                                String description,
                        @Context HttpServletResponse servletResponse) throws IOException {
        Todo todo = new Todo(id, summary);
        if (description != null) {
            todo.setDescription(description);
        }
        TodoDAO.instance.getModel().put(id, todo);
        servletResponse.sendRedirect("../create_todo.html");
    }

    public TodoResource getTodo(@PathParam("todo") String id) {
        return new TodoResource(uriInfo, request, id);
    }
}
