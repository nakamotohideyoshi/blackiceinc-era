package com.blackiceinc.era.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.ldap.server.ApacheDSContainer;

import javax.annotation.PreDestroy;

@Configuration
@Profile({Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA})
public class EmbeddedLdapServer {

    private static ApacheDSContainer server;
    private static Integer serverPort = 33389;

    @Autowired
    private Environment env;

    @Bean(name = "embededLdapServer")
    public ApacheDSContainer getEmbeddedLdapServer() throws Exception {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
            server = new ApacheDSContainer("dc=north,dc=vib,dc=corp",
                    "classpath:onsite_users.ldif");
            server.setPort(serverPort);
            server.afterPropertiesSet();

            return server;
        }

        return null;
    }

    @PreDestroy
    public void stopServer() throws Exception {
        if (server != null) {
            server.stop();
        }
    }
}
