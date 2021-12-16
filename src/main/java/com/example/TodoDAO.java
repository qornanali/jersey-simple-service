package com.example;

import java.util.HashMap;
import java.util.Map;

public enum TodoDAO {
    instance;

    private final Map<String, Todo> contentProvider = new HashMap<>();

    TodoDAO() {
        Todo todo = new Todo("1", "Learn REST");
        todo.setDescription("Read http://www.learnweb.com/tutorials/REST/article.html");
        contentProvider.put("1", todo);
        todo = new Todo("2", "Do something");
        todo.setDescription("Read complete http://www.amaron.com");
        contentProvider.put("2", todo);
    }

    public Map<String, Todo> getModel() {
        return contentProvider;
    }
}
