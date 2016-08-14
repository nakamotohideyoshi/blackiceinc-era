package com.blackiceinc.era;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class })
public class BlackiceincEraApplication {

    private static final Logger log = LoggerFactory.getLogger(BlackiceincEraApplication.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(BlackiceincEraApplication.class);

        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

        addEnvironmentDetectedProfile(app, source);

        Environment env = app.run(args).getEnvironment();
        log.info("Access URLs:\n----------------------------------------------------------\n\t" +
                        "Local: \t\thttp://127.0.0.1:{}\n\t" +
                        "External: \thttp://{}:{}\n----------------------------------------------------------",
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    /**
     * Detect environment and set proper profile. If nothing is detected set local.
     */
    private static void addEnvironmentDetectedProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") &&
                !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {

            String profile = EnvironmentDetection.detectEnvironmentProfile();
            log.info("Setting profile : {}", profile);
            app.setAdditionalProfiles(profile);
        } else {
            log.info("No environment detection is made. Outside configuration is made setting SPRING_PROFILES_ACTIVE : {} and spring.profiles.active : {}",
                    System.getenv().get("SPRING_PROFILES_ACTIVE"), source.getProperty("spring.profiles.active"));
        }
    }

    @PostConstruct
    public void initApplication() {
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }
}
