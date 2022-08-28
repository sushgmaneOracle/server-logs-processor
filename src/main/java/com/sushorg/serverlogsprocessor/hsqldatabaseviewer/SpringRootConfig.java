package com.sushorg.serverlogsprocessor.hsqldatabaseviewer;

import com.sushorg.serverlogsprocessor.configuration.DatabaseConfig;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@Import({DatabaseConfig.class})
@ComponentScan({ "com.sushorg.serverlogsprocessor" })
public class SpringRootConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

    //default username : sa, password : ''
    @PostConstruct
    public void getDbManager(){
        System.setProperty("java.awt.headless", "false");
        DatabaseManagerSwing.main(
                new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
    }
}
