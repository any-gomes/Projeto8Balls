package MeuRemedio;


import org.hibernate.Hibernate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class MainConfig {

    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }
}

/* @Configuration
public class DataConfiguration {

/*
   @Bean
    public DataSource datasource() {
        try {
            DriverManagerDataSource driver = new DriverManagerDataSource();
            driver.setDriverClassName("org.postgresql.Driver");
            driver.setUrl("jdbc:postgresql://localhost:5432/meuremediobd");
            driver.setUsername("postgres");
            driver.setPassword("root");

            return driver;

        } catch (Exception e) {
            throw new IllegalStateException("Erro de conexão com o banco" + e);
        }
    }


    /* @Bean
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
    }
}
*/