package org.hostel.controller;

import lombok.RequiredArgsConstructor;
import org.hostel.domain.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.exception.CategoryNotFoundException;
import org.hostel.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> remove(@PathVariable("id") long id) throws CategoryNotFoundException {
        return categoryService.remove(id);
    }

    @GetMapping("/categoryList")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<CategoryName>> getCategoryList() {
        return categoryService.getCategoryList();
    }
}
