package com.pickupsports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickupsports.json.ResourcesMapper;
import com.pickupsports.repository.Event;
import com.pickupsports.repository.EventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

/**
 * Created by clarkperkins on 10/23/14.
 *
 */

//Tell Spring to automatically inject any dependencies that are marked in
//our classes with @Autowired
@EnableAutoConfiguration
// Tell Spring to automatically create a JPA implementation of our
// VideoRepository
@EnableJpaRepositories(basePackageClasses = EventRepository.class)
// Tell Spring to turn on WebMVC (e.g., it should enable the DispatcherServlet
// so that requests can be routed to our Controllers)
@EnableWebMvc
// Tell Spring that this object represents a Configuration for the
// application
@Configuration
// Tell Spring to go and scan our controller package (and all sub packages) to
// find any Controllers or other components that are part of our applciation.
// Any class in this package that is annotated with @Controller is going to be
// automatically discovered and connected to the DispatcherServlet.

@ComponentScan
public class Application extends RepositoryRestMvcConfiguration {

    // Tell Spring to launch our app!

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // We are overriding the bean that RepositoryRestMvcConfiguration
    // is using to convert our objects into JSON so that we can control
    // the format. The Spring dependency injection will inject our instance
    // of ObjectMapper in all of the spring data rest classes that rely
    // on the ObjectMapper. This is an example of how Spring dependency
    // injection allows us to easily configure dependencies in code that
    // we don't have easy control over otherwise.
    //
    // Normally, we would not override this object mapping. However, in this
    // case, we are overriding the JSON conversion so that we can easily
    // extract a list of videos, etc. using Retrofit. You can remove this
    // method from the class to see what the default HATEOAS-based responses
    // from Spring Data Rest look like. You will need to access the server
    // from your browser as removing this method will break the Retrofit
    // client.
    //
    // See the ResourcesMapper class for more details.
    @Override
    public ObjectMapper halObjectMapper() {
        return new ResourcesMapper();
    }

    @Override
    protected void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config) {
        config.exposeIdsFor(Event.class);
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(dataSource());
        sf.setPackagesToScan("com.pickupsports.repository");

        // Hibernate properties for generating DB schema
        Properties hibernateProps = new Properties();
        hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProps.setProperty("hibernate.show_sql", "false");
        sf.setHibernateProperties(hibernateProps);

        return sf;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:"+System.getenv("DATABASE_URL"));
        ds.setUsername(System.getenv("DATABASE_USER"));
        ds.setPassword(System.getenv("DATABASE_PASS"));
        return ds;
    }

}

