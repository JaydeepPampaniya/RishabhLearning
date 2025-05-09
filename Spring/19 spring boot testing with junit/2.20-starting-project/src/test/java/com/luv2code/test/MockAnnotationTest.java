package com.luv2code.test;

import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = MockAnnotationTest.class)
public class MockAnnotationTest {
    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent firstStudent;

    @Autowired
    StudentGrades studentGrades;

    @Mock
    private ApplicationDao applicationDao;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void beforeEach(){
        firstStudent.setFirstname("Jaydeep");
        firstStudent.setLastname("Pampaniya");
        firstStudent.setEmailAddress("Jaydeep.pampaniya@rishabhsoft.com");
        firstStudent.setStudentGrades(studentGrades);

    }

    @DisplayName("When & Verify")
    @Test
    void assertEqualsTestAddGrades(){
        when(applicationDao.addGradeResultsForSingleClass(studentGrades.getMathGradeResults())).thenReturn(100.00);
        assertEquals(100,applicationDao.addGradeResultsForSingleClass(firstStudent.getStudentGrades().getMathGradeResults()));
    }

}
