package com.testing.business;

import com.testing.service.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {
    private final TodoService todoService;

    TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        List<String> filteredTodos = new ArrayList<>();
        List<String> allTodos = todoService.retrieveTodos(user);
        for (String todo : allTodos) {
            if (todo.contains("Spring")) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
}
