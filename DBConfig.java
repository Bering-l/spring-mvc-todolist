package com.todo.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

import static com.todo.constants.DBConstants.*;


@Configuration
@EnableTransactionManagement
public class DBConfig implements WebMvcConfigurer {
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setJdbcUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.todo.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        return sessionFactoryBean;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        //  hibernateProperties.setProperty(Environment.DIALECT, DB_DIALECT);
        hibernateProperties.setProperty(Environment.DRIVER, DB_DRIVER);
        hibernateProperties.setProperty(Environment.HBM2DDL_AUTO, "validate");
        return hibernateProperties;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(EntityManagerFactory factory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        return transactionManager;
    }
}
