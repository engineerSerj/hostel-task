package org.hostel;

import liquibase.integration.spring.SpringLiquibase;
import liquibase.pro.packaged.A;
import org.hostel.domains.Apartment;
import org.hostel.repositories.ApartmentRepository;
import org.hostel.service.ApartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanIsExistTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void checkBeanNotNull() {
        assertNotNull(context);
    }
}
