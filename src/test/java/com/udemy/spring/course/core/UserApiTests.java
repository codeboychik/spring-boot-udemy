package com.udemy.spring.course.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.lundegaard.test.task.userapi.UserApplication;
import eu.lundegaard.test.task.userapi.entity.Address;
import eu.lundegaard.test.task.userapi.entity.User;
import eu.lundegaard.test.task.userapi.exception.GlobalExceptionHandler;
import eu.lundegaard.test.task.userapi.repository.AddressRepository;
import eu.lundegaard.test.task.userapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
@Import(UserApplication.class)
public class UserApiTests {

    @TestConfiguration
    static class Config {
        @Bean
        public GlobalExceptionHandler globalExceptionHandler() {
            return new GlobalExceptionHandler();
        }
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }

    /*@Value("${spring.profiles.active}")
    private String activeProfile;*/

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testUserValidation() throws Exception {
        // correct payload
        mockMvc.perform(
            post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                    User.builder()
                        .email("test@gmail.com")
                        .username("test")
                    .build()
                ))
        ).andExpect(status().isCreated())
         .andExpect(jsonPath("$.username").value("test"));

        // invalid payload
        mockMvc.perform(
            post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                    User.builder()
                        .email("")
                        .username("")
                        .build()
                ))
        ).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors", hasItems(
            "Username shouldn't be blank or null",
            "must be a well-formed email address",
            "Username length must have 1-32 characters",
            "Must not be blank or contain whitespace"
        )));
    }

    @Test
    public void testUserModification() throws Exception {

        // calling api to create new user
        User u = objectMapper.readValue(mockMvc.perform(
            post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                    User.builder()
                        .email("test@gmail.com")
                        .username("test")
                        .build()
                ))
        ).andReturn().getResponse().getContentAsString(), User.class);

        // modify username only
        mockMvc.perform(
            put("/api/users/{userId}/edit", u.getId())
           .content(objectMapper.writeValueAsString(
               User.builder().username("andy").email(null).build()
           )).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        // modify email only
        mockMvc.perform(
            put("/api/users/{userId}/edit", u.getId())
                .content(objectMapper.writeValueAsString(
                    User.builder().username(null).email("1232131@mail.com").build()
                )).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        // invalid payload
        mockMvc.perform(
            put("/api/users/{userId}/edit", u.getId())
                .content(objectMapper.writeValueAsString(
                    User.builder().username("").email("").build()
                )).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors", hasItems(
            "Username shouldn't be blank or null",
            "must be a well-formed email address",
            "Username length must have 1-32 characters",
            "Must not be blank or contain whitespace"
        )));

    }

    @Test()
    public void testRemoval() throws Exception {

        // calling api to create new user
        User u = objectMapper.readValue(mockMvc.perform(
            post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                    User.builder()
                        .email("test@gmail.com")
                        .username("test")
                        .build()
                ))
        ).andReturn().getResponse().getContentAsString(), User.class);

        // add new address to this user
        Address a = objectMapper.readValue(
            mockMvc.perform(
                post("/api/users/{userId}/newAddress", u.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(
                       Address.builder()
                            .city("Brno")
                            .country("CZ")
                            .build()))
            ).andReturn().getResponse().getContentAsString(), Address.class
        );

        mockMvc.perform(delete("/api/users/{userId}", u.getId()));
        Assertions.assertFalse(userRepository.findById(u.getId()).isPresent());
        Assertions.assertFalse(addressRepository.findById(a.getId()).isPresent());
    }

    @Test
    public void testAddressModification() throws Exception {
        // calling api to create new user
        User u = objectMapper.readValue(mockMvc.perform(
            post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                    User.builder()
                        .email("test@gmail.com")
                        .username("test")
                        .build()
                ))
        ).andReturn().getResponse().getContentAsString(), User.class);

        // add new address to this user
        Address a = objectMapper.readValue(
            mockMvc.perform(
                post("/api/users/{userId}/newAddress", u.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(
                        Address.builder()
                            .city("Brno")
                            .country("CZ")
                            .build()))
            ).andReturn().getResponse().getContentAsString(), Address.class
        );

        // correct payload
        mockMvc.perform(
            put("/api/users/{userId}/editAddress/{addressId}", u.getId(), a.getId())
                .content(objectMapper.writeValueAsString(
                    Address.builder().city("New York").country("USA").build()
                )).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        // invalid payload
        mockMvc.perform(
            put("/api/users/{userId}/editAddress/{addressId}", u.getId(), a.getId())
                .content(objectMapper.writeValueAsString(
                    Address.builder().city(null).country(null).build()
                )).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

}
