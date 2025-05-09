package com.testing.business;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

 class HamcrestMatchersTest {
    @Test
    void test(){
        List<Integer> scores = Arrays.asList(99,100,101,105);
        assertThat(scores,hasSize(4));
        assertThat(scores,hasItems(99,100,105));
//        assertThat(scores,hasItem(108));
        assertThat(scores,everyItem(greaterThan(90)));
        assertThat("",isEmptyString());
        assertThat(null,isEmptyOrNullString());

        Integer[] marks = {1,2,3};
        assertThat(marks,arrayWithSize(3));
        assertThat(marks,arrayContaining(1,2,3));
        assertThat(marks,arrayContainingInAnyOrder(2,1,3));


        String actual = "Hello, World!";
        assertThat(actual, startsWith("Hello"));
        assertThat(actual, containsString("World"));
        assertThat(actual, endsWith("!"));

        assertThat(5, is(equalTo(5)));
        assertThat(5, is(5));
        assertThat("Java", is(not("Python")));

        assertThat("Hamcrest is awesome", containsString("awesome"));
        assertThat("JUnit", startsWith("J"));
        assertThat("Test", endsWith("t"));

//        assertThat(List.of("apple", "banana", "cherry"), hasItem("banana"));
//        assertThat(List.of(1, 2, 3, 4), hasSize(4));

        assertThat(10, greaterThan(5));
        assertThat(3.14, closeTo(3.0, 0.2));
    }
}
