package org.example.diplomska_idelek.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class OracleDatabaseConfiguration {

    @Value("${oracle.url}")
    private String url;
    @Value("${oracle.username}")
    private String username;
    @Value("${oracle.password}")
    private String password;

    @Bean
    public DataSource oracleDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate oralceJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
