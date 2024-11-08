package org.demolibrary47fs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demolibrary47fs.dto.AuthorRequestDto;
import org.demolibrary47fs.entity.Author;
import org.demolibrary47fs.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Author> authors;

    @BeforeEach
    void setUp() {

        Author author1 = new Author(1L, "Author 1", new ArrayList<>());
        Author author2 = new Author(2L, "Author 2", new ArrayList<>());

        authors = Arrays.asList(author1,author2);

    }

    @Test
    public void testGetAllAuthorsWhenReturnListOfAuthors() throws Exception {

        when(authorService.findAll()).thenReturn(authors);

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Author 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Author 2")));

    }

    @Test
    void createAuthorIfRequestIsValid() throws Exception {
        Author author = Author.builder()
                .id(3L)
                .name("Author 3")
                .build();

        AuthorRequestDto validAuthorRequest = new AuthorRequestDto("Author 3");



        when(authorService.addNewAuthor(any(AuthorRequestDto.class))).thenReturn(author);

        mockMvc.perform(post("/api/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validAuthorRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Author 3"))
    );
    }

    @Test
    void createAuthorIfRequestIsInvalid() throws Exception {

        AuthorRequestDto invalidAuthorRequest = new AuthorRequestDto(" ");


        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidAuthorRequest)))
                .andExpect(status().isBadRequest());
               // .andExpect(jsonPath("$.name").value("Author name is required"));
    }

}