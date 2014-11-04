package com.pickupsports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickupsports.json.ResourcesMapper;
import com.pickupsports.repository.Event;
import com.pickupsports.repository.EventRepository;
import com.sun.jdi.connect.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

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

    // This version uses the Tomcat web container and configures it to
    // support HTTPS. The code below performs the configuration of Tomcat
    // for HTTPS. Each web container has a different API for configuring
    // HTTPS.
    //
    // The app now requires that you pass the location of the keystore and
    // the password for your private key that you would like to setup HTTPS
    // with. In Eclipse, you can set these options by going to:
    //    1. Run->Run Configurations
    //    2. Under Java Applications, select your run configuration for this app
    //    3. Open the Arguments tab
    //    4. In VM Arguments, provide the following information to use the
    //       default keystore provided with the sample code:
    //
    //       -Dkeystore.file=src/main/resources/private/keystore -Dkeystore.pass=changeit
    //
    //    5. Note, this keystore is highly insecure! If you want more securtiy, you
    //       should obtain a real SSL certificate:
    //
    //       http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html
    //
    @Bean
    EmbeddedServletContainerCustomizer containerCustomizer(
            @Value("${keystore.file}") String keystoreFile,
            @Value("${keystore.pass}") final String keystorePass)
            throws Exception {


        // This is boiler plate code to setup https on embedded Tomcat
        // with Spring Boot:

        final String absoluteKeystoreFile = new File(keystoreFile)
                .getAbsolutePath();

        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
                tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {

                    @Override
                    public void customize(Connector connector) {
                        connector.setPort(8443);
                        connector.setSecure(true);
                        connector.setScheme("https");

                        Http11NioProtocol proto = (Http11NioProtocol) connector
                                .getProtocolHandler();
                        proto.setSSLEnabled(true);

                        // If you update the keystore, you need to change
                        // these parameters to match the keystore that you generate
                        proto.setKeystoreFile(absoluteKeystoreFile);
                        proto.setKeystorePass(keystorePass);
                        proto.setKeystoreType("JKS");
                        proto.setKeyAlias("tomcat");

                    }
                });
            }

        };
    }

}

