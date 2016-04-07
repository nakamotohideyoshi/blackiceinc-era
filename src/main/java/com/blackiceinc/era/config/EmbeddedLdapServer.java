package com.blackiceinc.era.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private ApacheDSContainer server;

    private static final Logger log = LoggerFactory.getLogger(EmbeddedLdapServer.class);

    @Autowired
    private Environment env;

    @Bean(name = "embededLdapServer")
    public ApacheDSContainer getEmbeddedLdapServer() {
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_LOCAL, Constants.SPRING_PROFILE_DEV_ERA)) {
            try {
                server = new ApacheDSContainer("dc=north,dc=vib,dc=corp",
                        "classpath:onsite_users.ldif");
                server.setPort(33389);
                server.afterPropertiesSet();
                return server;
            } catch (Exception ex) {
                log.error("Error creating embedded ldap server", ex);
                return null;
            }
        }

        return null;
    }

    @PreDestroy
    public void stopServer() throws Exception {
        if (server != null) {
            log.info("Stopping embedded ldap server.");
            server.stop();
        }else{
            log.info("Embedded ldap server instance is null. No need to stop.");
        }
    }
}
