package com.usersapi.endpoints.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersapi.UsersApiApplication;
import com.usersapi.domain.user.User;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import springfox.documentation.spring.web.json.Json;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = UsersApiApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    private void instantiateNewUser() {
        User userNumberFour = new User();
        userNumberFour.setName("Four");
        userNumberFour.setId(4L);

        ResponseEntity<User> responseEntity = restTemplate
                .postForEntity(createURLWithPort("/users"), userNumberFour, User.class);
    }

    @Test
    public void createNewUserTest() {
        User testUser = new User();
        testUser.setName("Test User");
        testUser.setId(5L);

        ResponseEntity<User> responseEntity = restTemplate
                .postForEntity(createURLWithPort("/users"), testUser, User.class);

        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(responseEntity.getBody(), testUser);
    }


    @Test
    public void listSpecificUserTest() throws JSONException {

        instantiateNewUser();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/users/4/"),
                HttpMethod.GET, httpEntity, String.class);

        String expectedResponseBody = "{id:4,name:Four}";

        assertEquals(200, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), false);
    }


    @Test
    public void listAllUsersTest() throws JSONException {

        instantiateNewUser();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/users"),
                HttpMethod.GET, httpEntity, String.class);

        //All instantiated users
        ArrayList<String> expectedResponseBody = new ArrayList<>(Collections.emptyList());
        expectedResponseBody.add("{id:1,name:Neo}");
        expectedResponseBody.add("{id:2,name:Owt}");
        expectedResponseBody.add("{id:3,name:Three}");
        expectedResponseBody.add("{id:4,name:Four}");

        assertEquals(200, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(String.valueOf(expectedResponseBody), responseEntity.getBody(), false);
    }

    @Test
    public void deleteSpecificUserTest() throws JSONException {

        instantiateNewUser();
        HttpEntity<String> httpEntity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/users/4/"),
                HttpMethod.DELETE, httpEntity, String.class);

        assertEquals(204, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(null, responseEntity.getBody(), false);
    }

    @Test
    public void updateSpecificUserTest() throws JSONException, JsonProcessingException {

        instantiateNewUser();

        User updatedUser = new User();
        updatedUser.setName("Updated");
        updatedUser.setId(4L);

        ObjectMapper mapper = new ObjectMapper();
        String requestBody = mapper.writeValueAsString(updatedUser);

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createURLWithPort("/users/4/"),
                HttpMethod.PUT, httpEntity, String.class);

        String expectedResponseBody = "{id:4,name:Updated}";

        assertEquals(200, responseEntity.getStatusCodeValue());
        JSONAssert.assertEquals(expectedResponseBody, responseEntity.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
