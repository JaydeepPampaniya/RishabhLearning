package com.testing.business;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.testing.service.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
class TodoBusinessImplMockitoInjectMocksTest {
    @Mock
    TodoService todoService;

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    void usingMockito() {
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");
        assertEquals(2, todos.size());
    }

    @Test
    void usingMockitoWithEmptyList() {
        List<String> allTodos = Arrays.asList();
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Dummy");
        assertEquals(0, todos.size());
    }

    @Test
    void usingMockito_usingBBD() {
        //Given
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        given(todoService.retrieveTodos("Ranga")).willReturn(allTodos);
        //when
        List<String> todos = todoBusinessImpl
                .retrieveTodosRelatedToSpring("Ranga");

        //then
        Assertions.assertThat(todos).hasSize(2).contains("Learn Spring MVC", "Learn Spring");
    }

    @Test
    void letsDeleteNow(){
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
        List<String> allTodos = Arrays.asList("Learn Spring MVC",
                "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Ranga")).thenReturn(allTodos);
//ArgumentCaptor is used to capture the arguments passed to a method call so they can be asserted later.
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Ranga");
        verify(todoService).deleteTodo(argumentCaptor.capture()); //Ranga
        then(todoService).should(times(2)).deleteTodo(argumentCaptor.capture());
        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }


    @Test
     void creatingASpyOnArrayList() {
        List<String> listSpy = spy(ArrayList.class);
        listSpy.add("Ranga");
        listSpy.add("in28Minutes");

        verify(listSpy).add("Ranga");
        verify(listSpy).add("in28Minutes");

        assertEquals(2, listSpy.size());
        assertEquals("Ranga", listSpy.get(0));
    }

    @Test
     void creatingASpyOnArrayList_overridingSpecificMethods() {
        List<String> listSpy = spy(ArrayList.class);
        listSpy.add("Ranga");
        listSpy.add("in28Minutes");

        stub(listSpy.size()).toReturn(-1);

        assertEquals(-1, listSpy.size());
        assertEquals("Ranga", listSpy.get(0));

        // @Spy Annotation
    }
}
