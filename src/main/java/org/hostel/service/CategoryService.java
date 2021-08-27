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

    public ResponseEntity<CategoryDto> add(CategoryDto categoryDto) {
        return new ResponseEntity<>(new CategoryDto(categoryRepository.save(new Category(categoryDto))), HttpStatus.CREATED);
    }

    public ResponseEntity<CategoryDto> remove(long id) throws CategoryNotFoundException {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<CategoryName>> getCategoryList() {
        List<CategoryName> categoryList = new ArrayList<>(Arrays.asList(CategoryName.values()));
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
}
