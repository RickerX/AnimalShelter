package com.example.animalshelter;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.json.JSONObject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CatDogControllersTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TelegramBot telegramBot;

    @Test
    void givenNoCatsInDatabase_whenGetCats_thenEmptyJsonArray() throws Exception{

        mockMvc.perform(get("/api/cat"))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$").isArray())
                .andExpect( jsonPath("$").isEmpty());
    }

    @Test
    void givenNoCatsInDatabase_whenCatAdded_thenItExistsInList() throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("breed","testBreed");

        mockMvc.perform(post("/api/cat/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.breed").value("testBreed"));

        mockMvc.perform(get("/api/cat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].breed").value("testBreed"));

    }

    @Test
    void givenNoDogsInDatabase_whenGetDogs_thenEmptyJsonArray() throws Exception{

        mockMvc.perform(get("/api/dog"))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$").isArray())
                .andExpect( jsonPath("$").isEmpty());
    }

    @Test
    void givenNoDogsInDatabase_whenDogAdded_thenItExistsInList() throws Exception{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("breed","testBreed");

        mockMvc.perform(post("/api/dog/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.breed").value("testBreed"));

        mockMvc.perform(get("/dog/cat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].breed").value("testBreed"));

    }

}
