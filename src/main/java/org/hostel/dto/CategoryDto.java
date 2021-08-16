package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domains.Category;
import org.hostel.domains.CategoryName;

@Data
@RequiredArgsConstructor
public class CategoryDto {

        private int id;
        private CategoryName categoryName;
        private String description;

        public CategoryDto (Category category){
            this.id = category.getId();
            this.categoryName = category.getCategoryName();
            this.description = category.getDescription();
        }
    }

