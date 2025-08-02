package org.example.ecommercespring.controllers;

import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.exception.GlobalExceptionHandler;
import org.example.ecommercespring.services.ICategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(GlobalExceptionHandler.class).build();
    }

    @Test
    @DisplayName("GET /api/categories should return all categories")
    void getAllCategories_shouldReturnAllCategories() throws Exception {
       //Arrange
        List<CategoryDTO> categories = new ArrayList<>();
        categories.add(CategoryDTO.builder().name("Electronics").id(1L).build());
        categories.add(CategoryDTO.builder().name("Books").id(2L).build());
        categories.add(CategoryDTO.builder().name("Clothings").id(3L).build());

        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);
        mockMvc.perform(get("/api/categories").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Books"))
                .andExpect(jsonPath("$[2].id").value(3))
                .andExpect(jsonPath("$[2].name").value("Clothings"));

        //assert
        Mockito.verify(categoryService, Mockito.times(1)).getAllCategories();

    }
}
