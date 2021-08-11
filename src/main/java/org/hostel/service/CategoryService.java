package org.hostel.service;

import lombok.RequiredArgsConstructor;
import org.hostel.domains.Category;
import org.hostel.domains.CategoryName;
import org.hostel.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public Category add(Category category) {
       return categoryRepository.save(category);
    }

    public void remove(int id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryName> getListCategory() {
        return new ArrayList<CategoryName>(Arrays.asList(CategoryName.values()));
    }
}
