package org.example.ecommercespring.services;

import org.example.ecommercespring.dto.CategoryDTO;
import org.example.ecommercespring.entity.Category;
import org.example.ecommercespring.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //This annotation is used to enable Mockito for the test class with Junit
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;



    @Test
    @DisplayName("Should return all categories successfully")
    void getAllCategories_shouldReturnAllCategories() {
        //Arrange
        List<Category> categories = new ArrayList<>();
        Category category1 = Category.builder()
                .name("Electronics")
                .build();
        category1.setId(1L);
        Category category2 = Category.builder()
                .name("Books")
                .build();
        category2.setId(2L);
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        //Act
        List<CategoryDTO> result = categoryService.getAllCategories();

        //Assert
        assertEquals(result.size(), categories.size());

        verify(categoryRepository,times(1)).findAll();
    }
}
