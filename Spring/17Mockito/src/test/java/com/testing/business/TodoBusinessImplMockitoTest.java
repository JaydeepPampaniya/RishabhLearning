package com.testing.business;
import com.testing.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoBusinessImplMockitoTest {

    @Test
     void usingMockito() {
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
     void usingMockitoWithEmptyList() {
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList();
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Dummy");
        assertEquals(0, todos.size());
    }

    @Test
    void usingMockito_usingBBD() {
        //Given
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
        //when
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");

        //then
        assertThat(todos).hasSize(2).contains("Learn Spring MVC", "Learn Spring");
    }

    @Test
    void letsDeleteNow(){
        TodoService todoService = mock(TodoService.class);
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");


        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);

        TodoBusinessImpl todoBusiness = new TodoBusinessImpl(todoService);
        todoBusiness.deleteTodosNotRelatedToSpring("Ranga");
//verify() is used in Mockito to check if a method on a mock object was called with expected arguments and how many times it was called.
        verify(todoService).deleteTodo("Learn to Dance");
//These ensure that deleteTodo() was never called with "Learn Spring MVC" and "Learn Spring".
        verify(todoService, never()).deleteTodo("Learn Spring MVC");

        verify(todoService, never()).deleteTodo("Learn Spring");

        verify(todoService, times(1)).deleteTodo("Learn to Dance");
        // atLeastOnce, atLeast


        then(todoService).should().deleteTodo("Learn to Dance");

        then(todoService).should(never()).deleteTodo("Learn Spring MVC");

        then(todoService).should(never()).deleteTodo("Learn Spring");
    }

    @Test
    void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor
                .forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
//ArgumentCaptor is used to capture the arguments passed to a method call so they can be asserted later.
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        verify(todoService).deleteTodo(argumentCaptor.capture()); //Ranga
        then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }
}