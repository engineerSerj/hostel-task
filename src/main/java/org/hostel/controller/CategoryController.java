package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") long id) {
        return categoryService.remove(id);
    }

    @GetMapping("/categoryList")
    public ResponseEntity<List<CategoryName>> getCategoryList() {
        return categoryService.getCategoryList();
    }
}
