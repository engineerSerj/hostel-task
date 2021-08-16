package org.hostel.controllers;

import lombok.RequiredArgsConstructor;
import org.hostel.domains.CategoryName;
import org.hostel.dto.CategoryDto;
import org.hostel.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping()
    public String add(Model model, @ModelAttribute("category") CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return "redirect:/category";
    }

    @GetMapping("/new")
    public String newCategory(@ModelAttribute("category") CategoryDto categoryDto) {
        return "category/new";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        categoryService.remove(id);
        return "redirect:/category";
    }

    @GetMapping("/categoryList")
    public String showApartment(Model model) {
        model.addAttribute("Categories", CategoryName.values());
        return "category/categoryList";
    }
}
