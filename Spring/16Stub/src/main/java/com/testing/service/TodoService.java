package com.testing.service;

import java.util.List;

public interface TodoService {
    List<String> retrieveTodos(String user);
}