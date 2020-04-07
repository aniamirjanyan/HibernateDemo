package com.example.demo;

import com.example.demo.api.BookController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Hibernate1ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @Autowired
    private ObjectMapper objectMapper;

    // test GET /find by author
    @Test
    public void testGetPage() throws Exception {
        mockMvc.perform(get("/api/findbyauthor/?author=Gillian Flynn"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 19,\n" +
                        "        \"title\": \"Gone Girl\",\n" +
                        "        \"author\": \"Gillian Flynn\",\n" +
                        "        \"year\": 2012,\n" +
                        "        \"text\": \"eng\"\n" +
                        "    }\n" +
                        "]"));
    }

    // test POST /add book
    @Test
    public void testPostPage() throws Exception {
        Book book = new Book("title", "author", (long) 1900, "eng");

        mockMvc.perform(post("/api/addbook")
                .contentType("application/json")
                //.param("sendWelcomeMail", "true")
                .content("{\"title\":\"title1\",\"author\":\"author1\",\"year\":\"1900\",\"text\":\"text1\"}"))
                .andExpect(status().isOk());
/*				.andExpect(content().json("[\n" +
						"    {\n" +
						"        \"id\": 19,\n" +
						"        \"title\": \"Gone Girl\",\n" +
						"        \"author\": \"Gillian Flynn\",\n" +
						"        \"year\": 2012,\n" +
						"        \"text\": \"eng\"\n" +
						"    }\n" +
						"]"));*/
    }

    // test GET /find by title and author
    @Test
    public void testGetTitleAndAuthor() throws Exception {
        mockMvc.perform(get("/api/findbytitleandauthor/?title=Gone Girl&author=Gillian Flynn"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n" +
                        "    {\n" +
                        "        \"id\": 19,\n" +
                        "        \"title\": \"Gone Girl\",\n" +
                        "        \"author\": \"Gillian Flynn\",\n" +
                        "        \"year\": 2012,\n" +
                        "        \"text\": \"eng\"\n" +
                        "    }\n" +
                        "]"));
    }

    // test GET / ultimate find
    @Test
    public void testUltimateFind() throws Exception {
        mockMvc.perform(get("/api/ultimatefind/?author=Dan Brown"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[{\"id\":17,\"title\":\"The Da Vinci Code\",\"author\":\"Dan Brown\",\"year\":2009,\"text\":\"eng\"}," +
                                "{\"id\":18,\"title\":\"The Da Vinci Code\",\"author\":\"Dan Brown\",\"year\":2009,\"text\":\"eng\"}]"));

    }
}


