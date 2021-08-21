package org.hostel.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;

@Data
@RequiredArgsConstructor
public class CategoryDto {

        private long id;
        private CategoryName categoryName;
        private String description;
    }

