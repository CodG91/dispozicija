package org.example.diplomska_idelek.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

//@Configuration
public class LiquibaseConfiguration {

    private static final String ORACLE_CHANGELOG = "classpath:liquibase/oracle-changelog.xml";
    private static final String POSTGRES_CHANGELOG = "classpath:liquibase/postgres-changelog.xml";

//    @Bean
//    public SpringLiquibase oracleLiquibase(@Qualifier("oracleDataSource") DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog(ORACLE_CHANGELOG);
//        return liquibase;
//    }

//    @Bean
//    public SpringLiquibase postgresLiquibase(@Qualifier("postgresDataSource") DataSource dataSource) {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setDataSource(dataSource);
//        liquibase.setChangeLog(POSTGRES_CHANGELOG);
//        return liquibase;
//    }

//    @Bean
//    public DataSourceInitializer oracleDataSourceInitializer(@Qualifier("oracleDataSource") DataSource dataSource) {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        initializer.setDatabasePopulator(databasePopulator(ORACLE_CHANGELOG));
//        return initializer;
//    }

//    @Bean
//    public DataSourceInitializer postgresDataSourceInitializer(@Qualifier("postgresDataSource") DataSource dataSource) {
//        DataSourceInitializer initializer = new DataSourceInitializer();
//        initializer.setDataSource(dataSource);
//        initializer.setDatabasePopulator(databasePopulator(POSTGRES_CHANGELOG));
//        return initializer;
//    }

//    private ResourceDatabasePopulator databasePopulator(String changelogPath) {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        Resource[] resources = loadResources(changelogPath);
//        populator.addScripts(resources);
//        return populator;
//    }
//
//    private Resource[] loadResources(String path) {
//        try {
//            return new PathMatchingResourcePatternResolver().getResources(path);
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to load resources", e);
//        }
//    }
}
