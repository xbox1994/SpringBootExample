package com.wtytest.controller;

import com.wtytest.dao.UserRepository;
import com.wtytest.entity.User;
import com.wtytest.helper.base.BaseTest;
import com.wtytest.helper.builder.UserBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends BaseTest{
    @Autowired
    private UserRepository userRepository;

    private User givenUser;
    private User returnUser;

    @BeforeEach
    public void setUp() {
        givenUser = UserBuilder.builder()
                .withDefault()
                .withAge(2008)
                .withName("wty")
                .build();
        returnUser = userRepository.save(givenUser);
    }

    @AfterEach
    public void afterAll() {
        userRepository.delete(returnUser);
    }

    @Test
    public void should_return_given_user() throws Exception {
        String response = this.mvc
                .perform(get("/users/" + returnUser.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        JSONObject jsonObject = new JSONObject(response);
        assertAll( "user", () -> {
            assertEquals(2008, jsonObject.getInt("age"));
            assertEquals("wty", jsonObject.getString("name"));
        } );
    }
}
