package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.repositoriy.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> add(CategoryDto categoryDto) {
        categoryRepository.save(new Category(categoryDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> remove(long id) {
        categoryRepository.deleteById(id);
        return !categoryRepository.findById(id).isPresent() ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    public ResponseEntity<List<CategoryName>> getCategoryList() {
        List<CategoryName> categoryList = new ArrayList<>(Arrays.asList(CategoryName.values()));
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    public Category getById(long id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
