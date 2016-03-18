package com.blackiceinc.era.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.ldap.server.ApacheDSContainer;

import javax.annotation.PreDestroy;

@Configuration
@Profile({Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_LOCAL})
public class EmbeddedLdapServer {

    private static ApacheDSContainer server;
    private static Integer serverPort = 33389;

    @Bean(name = "embededLdapServer")
    public ApacheDSContainer getEmbeddedLdapServer() throws Exception {
        server = new ApacheDSContainer("dc=springframework,dc=org",
                "classpath:users.ldif");
        server.setPort(serverPort);
        server.afterPropertiesSet();

        return null;
    }

    @PreDestroy
    public void stopServer() throws Exception {
        if (server != null) {
            server.stop();
        }
    }
}
