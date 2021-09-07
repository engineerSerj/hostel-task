package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.repositoriy.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = "categories")
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public ResponseEntity<CategoryDto> add(CategoryDto categoryDto) {
        if (categoryRepository.existsByCategoryName(categoryDto.getCategoryName())) {
            logger.error("category already exists category name {}", categoryDto.getCategoryName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Category category = categoryRepository.save(new Category(categoryDto));
        logger.info("add category with id {}", category.getId());
        return new ResponseEntity<>(new CategoryDto(category), HttpStatus.CREATED);
    }

    public ResponseEntity<CategoryDto> remove(long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            logger.error("category not found with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("remove category with id {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Cacheable("categories")
    public ResponseEntity<List<CategoryName>> getCategoryList() {
        List<CategoryName> categoryList = new ArrayList<>(Arrays.asList(CategoryName.values()));
        logger.info("get category list");
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
