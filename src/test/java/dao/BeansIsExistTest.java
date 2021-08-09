package dao;

import liquibase.integration.spring.SpringLiquibase;
import org.hostel.dao.ApartmentDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hostel.config.SpringConfig;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
//@WebAppConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes= SpringConfig.class)
public class BeansIsExistTest {
    @Autowired
    ApplicationContext context;
    @Autowired
    SpringLiquibase springLiquibase;
    @Autowired
    LocalSessionFactoryBean sessionFactory;
    @Autowired
    Properties hibernateProperties;
    @Autowired
    DataSource dataSource;
    @Autowired
    ApartmentDaoImpl apartmentDAOImpl;

    @Test
    public void checkBeanNotNull(){
        assertNotNull(context);
        assertNotNull(sessionFactory);
        assertNotNull(hibernateProperties);
        assertNotNull(dataSource);
        assertNotNull(springLiquibase);
    }

    @Test
    public void checkBeanDaoNotNull(){
        assertNotNull(apartmentDAOImpl);

    }

   /* @Test
    public void getID(){
        assertNotNull(apartmentDAOImpl.getById(34).toString());
    }*/
}
