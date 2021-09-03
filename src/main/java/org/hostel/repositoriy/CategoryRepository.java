package org.hostel.repositoriy;

import org.hostel.domain.Category;
import org.hostel.domain.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(CategoryName categoryName);
}
