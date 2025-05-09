package com.appsdeveloperblog.UsersService.ui;

import com.appsdeveloperblog.UsersService.ui.model.User;
import com.appsdeveloperblog.UsersService.ui.model.UserRest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@ActiveProfiles("test")
class UsersControllerWithTestContainerITest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:9.2.0");

    @LocalServerPort
    private int localServerPort;

//    private final RequestLoggingFilter requestLoggingFilter = RequestLoggingFilter.with(LogDetail.BODY,LogDetail.HEADERS);
    private final RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(); // for all filters
    private final ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter();



    @Order(1)
    @Test
    void testContainerIsRunning() {
        assertTrue(mysqlContainer.isRunning());
    }

    @BeforeAll
    void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = localServerPort;
        RestAssured.filters(requestLoggingFilter,responseLoggingFilter);

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(RestAssured.baseURI)
                .build();

    }
    @Test
    @Order(2)
    void testCreateUser_whenValidDetailsProvided_returnCreateUser(){
        // Arrange

        User newUser = new User("Jaydeep","Pampaniya","jaydeep.pampaniya@rishabhsoft.com","Jaydeep@77");

        // Act

        given()
                .body(newUser)
                .when().post("/users")
                .then().statusCode(201).body("id",notNullValue()).body("firstName",equalTo(newUser.getFirstName())).body("lastName",equalTo(newUser.getLastName()));



        given().log().all() // you can log body, all, headers,parameters and etc
                .body(newUser)
                .when().post("/users")
                .then().statusCode(201).body("id",notNullValue()).body("firstName",equalTo(newUser.getFirstName())).body("lastName",equalTo(newUser.getLastName()));


        given().log().all() // you can log body, all, headers,parameters and etc
                .body(newUser)
                .when().post("/users")
                .then().log().all()
                .statusCode(201).body("id",notNullValue()).body("firstName",equalTo(newUser.getFirstName())).body("lastName",equalTo(newUser.getLastName()));



//        Response response = given()
//                .headers(headers)
//                .body(newUser)
//                .when().post("/users")
//                .then().extract().response();
//
//        // Assert
//
//        assertEquals(201,response.statusCode());
//        assertEquals(newUser.getFirstName(),response.jsonPath().getString("firstName"));
//        assertEquals(newUser.getLastName(),response.jsonPath().getString("lastName"));
//        assertEquals(newUser.getEmail(),response.jsonPath().getString("email"));
//        assertNotNull(response.jsonPath().getString("id"));
    }

}
