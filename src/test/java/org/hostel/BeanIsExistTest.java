package org.hostel;

import lombok.RequiredArgsConstructor;
import org.hostel.repositories.ApartmentRepository;
import org.hostel.repositories.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanIsExistTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ApartmentRepository apartmentRepository;

    @Autowired
    ApplicationContext context;

    @Test
    public void checkBeanNotNull() {
        assertNotNull(context);
        assertNotNull(apartmentRepository);
        assertNotNull(categoryRepository);
    }
}
