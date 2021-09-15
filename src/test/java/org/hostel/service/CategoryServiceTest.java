package org.hostel.service;

import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.repositoriy.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceTest.class);

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void canAdd() {
        CategoryDto category = new CategoryDto();
        category.setId(1L);
        category.setCategoryName(CategoryName.APARTMENT);

        try{
            categoryService.add(category);
        } catch (Exception e){
            //throws expected NullPointerException because
            //userService method is trying to get result - Category category = categoryRepository.save(new Category(categoryDto))
            e.printStackTrace();
        }
        Category categoryFromDTO = new Category(category);
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);

        verify(categoryRepository).save(categoryArgumentCaptor.capture());
        Category capturedCategory = categoryArgumentCaptor.getValue();
        System.out.println(capturedCategory.toString() + "\ncompared to \n" + categoryFromDTO);
        assertThat(capturedCategory).isEqualTo(categoryFromDTO);
    }

    @Test
    void canRemove() {
        categoryService.remove(1L);
        verify(categoryRepository).findById(1L);
        // if apartment with id 1L is present this categoryRepository delete by id
    }

    @Test
    void canGetCategoryList(){
        assertThat(categoryService.getCategoryList()).isEqualTo(new ResponseEntity<>(Arrays.asList(CategoryName.values()), HttpStatus.OK));
    }
}