package org.hostel.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hostel.dto.CategoryDto;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;
    private String description;

    public Category (CategoryDto category){
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.description = category.getDescription();
    }
}
