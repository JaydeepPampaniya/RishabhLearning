package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import static org.junit.jupiter.api.Assertions.*;

import com.sun.jdi.connect.Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
class ApplicationExampleTest {

    private static  int count=0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;


    @Autowired
    CollegeStudent student;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    void beforeEach(){
        count = count+1;
        System.out.println("Testing: " + appInfo +" Which is "+ appDescription + " Version: " +appVersion + ". Execution od test method "+ count);
        student.setFirstname("Jaydeep");
        student.setLastname("Pampaniya");
        student.setEmailAddress("jaydeep.pampaniya@rishabhsoft.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,85.0,76.50,91.75)));
        student.setStudentGrades(studentGrades);

    }
    @DisplayName("Add grade results for student grades")
    @Test
    void addGradeResultsForStudentGrades(){
        assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Add grade results for student grades not equal")
    @Test
    void addGradeResultsForStudentGradesNotEqual(){
        assertNotEquals(0,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Is grade greater")
    @Test
    void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90,75),"failure - should be true");
    }

    @DisplayName("Is grade greater false")
    @Test
    void isGradeGreaterStudentGradesFalse(){
        assertFalse(studentGrades.isGradeGreater(90,95),"failure - should be false");
    }

    @DisplayName("No values")
    @Test
    void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),"Object should not be null");
    }

    @DisplayName("Create student without grade init")
    @Test
    void createStudentWithoutGradesInit(){
        CollegeStudent studentTwo = context.getBean("collegeStudent",CollegeStudent.class);
        studentTwo.setFirstname("Jaydeep");
        studentTwo.setLastname("Pampaniya");
        studentTwo.setEmailAddress("jaydeep.pampaniya@rishabhsoft.com");
        assertNotNull(studentTwo.getFirstname());
        assertNotNull(studentTwo.getLastname());
        assertNull(studentGrades.checkNull(studentTwo.getStudentGrades()));
        assertNotNull(studentTwo.getEmailAddress());
    }

    @DisplayName("Verify students are prototypes")
    @Test
    void verifyStudentsArePrototypes(){
        CollegeStudent studentTwo = context.getBean("collegeStudent",CollegeStudent.class);
        assertNotSame(student,studentTwo);
    }

    @DisplayName("Find Grade Point Average")
    @Test
    void findGradePointAverage(){
        assertAll("Testing all assertEquals",
                ()->assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades().getMathGradeResults())),
                ()->assertEquals(88.31,student.getStudentGrades().getMathGradeResults()));
    }

}
