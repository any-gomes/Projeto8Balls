package MeuRemedio;


import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataConfiguration {


   @Bean
    public DataSource datasource() {
        try {
            DriverManagerDataSource driver = new DriverManagerDataSource();
            driver.setDriverClassName("org.postgresql.Driver");
            driver.setUrl("jdbc:postgresql://<ec2-52-86-56-90.compute-1.amazonaws.com>:<5432>/<dgiqcipblns01>?sslmode=require&user=<flwjxtugljoktu>&password=<2388799cf32e5267930270d9ca53b630eb08df68dd6f51782014e256a0af8742>");
            driver.setUsername("flwjxtugljoktu");
            driver.setPassword("2388799cf32e5267930270d9ca53b630eb08df68dd6f51782014e256a0af8742");

            return driver;

        } catch (Exception e) {
            throw new IllegalStateException("Erro de conexão com o banco" + e);
        }
    }



    /*@Bean
    public JpaVendorAdapter JpaVendorAdapter() {
        try {
            HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            adapter.setDatabase(Database.POSTGRESQL);
            adapter.setShowSql(true);
            adapter.setGenerateDdl(true);
            adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQL10Dialect");
            adapter.setPrepareConnection(true);


            return adapter;
        } catch (Exception e) {
            throw new IllegalArgumentException("Hibernate error " + e);
        }
    }*/
}
