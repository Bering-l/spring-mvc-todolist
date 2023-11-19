package com.todo.constants;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DBConstants {
    public static final String DB_DRIVER = "com.p6spy.engine.spy.P6SpyDriver";
    public static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Helvetica17";
    public static final String DB_URL = "jdbc:p6spy:mysql://localhost:3306/todo";
}
