package com.testing.business;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;


import java.util.List;

class ListTest {
    @Test
    void letsMockListSize(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        Assertions.assertEquals(2,listMock.size());
    }
    @Test
    void letsMockListSize_ReturnMultipleValues(){
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);
        Assertions.assertEquals(2,listMock.size());
        Assertions.assertEquals(3,listMock.size());
    }

    @Test
    void letsMockListGetSize(){
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("0 position");
        Assertions.assertEquals("0 position",listMock.get(0));
        Assertions.assertNull(listMock.get(1));
    }

    @Test
    void letsMockListsGetWithAny(){
        List<String> list = mock(List.class);
        when(list.get(anyInt())).thenReturn("in28Minutes");
        Assertions.assertEquals("in28Minutes", list.get(0));
        Assertions.assertEquals("in28Minutes", list.get(1));
    }

    @Test
    void letsMockLists_ThrowsException(){
        List<String> list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new RuntimeException("Something wrong"));
        Assertions.assertThrows(RuntimeException.class, ()->list.get(0));


    }
}
