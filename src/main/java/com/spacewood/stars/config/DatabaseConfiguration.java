/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacewood.stars.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author webdesign
 */
@Configuration
@Profile("container")
public class DatabaseConfiguration {
   @Value("${stars.db.driver_class}")
    private String driverClassname;

    @Value("${stars.db.connection_string}")
    private String connectionString;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setProperties(getMySqlProperties());
        cpds.setDriverClass(driverClassname);
        cpds.setJdbcUrl(connectionString);
        cpds.setAcquireIncrement(2);
        return cpds;
    }

    public Properties getMySqlProperties() {
        Properties mysqlProperties = new Properties();
        mysqlProperties.setProperty("characterEncoding", "UTF-8");
        mysqlProperties.setProperty("useUnicode", "true");
        return mysqlProperties;
    } 
}
