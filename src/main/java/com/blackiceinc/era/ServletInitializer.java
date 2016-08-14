package com.blackiceinc.era;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.core.env.AbstractEnvironment;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ServletInitializer extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(ServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        log.info("ServletInitializer configure Spring Boot");

        return application.sources(BlackiceincEraApplication.class);
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (!System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            String profile = EnvironmentDetection.detectEnvironmentProfile();
            log.info("Setting profile : {}", profile);
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
        } else {
            log.info("No environment detection is made. Outside configuration is made setting SPRING_PROFILES_ACTIVE : {}",
                    System.getenv().get("SPRING_PROFILES_ACTIVE"));
        }
        super.onStartup(servletContext);
    }
}
