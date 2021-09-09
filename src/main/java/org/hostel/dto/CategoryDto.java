package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;

@Data
@RequiredArgsConstructor
public class CategoryDto {

    public CategoryDto(long id) {
        this.id = id;
    }

    private long id;
        private CategoryName categoryName;
        private String description;

    public CategoryDto(Category category) {
        this.id=category.getId();
        this.categoryName=category.getCategoryName();
        this.description=category.getDescription();
    }

}

