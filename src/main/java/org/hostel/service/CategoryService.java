package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.Exceptions.CategoryNotFoundException;
import org.hostel.domains.Category;
import org.hostel.domains.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.repositories.CategoryRepository;
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

    public void add(CategoryDto categoryDto) {
        categoryRepository.save(new Category(categoryDto));
    }

    public void remove(int id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryName> getListCategory() {
        return new ArrayList<CategoryName>(Arrays.asList(CategoryName.values()));
    }

    public Category getById(int id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }
}
