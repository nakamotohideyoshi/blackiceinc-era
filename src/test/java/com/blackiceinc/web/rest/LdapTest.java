package com.blackiceinc.web.rest;

import com.blackiceinc.era.BlackiceincEraApplication;
import com.blackiceinc.era.services.security.LdapAuthenticateService;
import com.blackiceinc.era.services.security.model.LdapConfig;
import com.blackiceinc.era.services.security.model.LdapConfigBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BlackiceincEraApplication.class)
@WebAppConfiguration
@IntegrationTest
@org.springframework.context.annotation.Configuration
public class LdapTest {

    private static Logger logger = LoggerFactory.getLogger(LdapTest.class);

    @Autowired
    private LdapAuthenticateService ldapAuthenticateService;

    @Test
    public void testLdap() {
        LdapConfig ldapConfig = new LdapConfigBuilder()
                .setLdapServers(Arrays.asList("ldap.forumsys.com"))
                .setLdapServerPort(389).setBaseDn("dc=example,dc=com")
                .setBindDn("cn=read-only-admin,dc=example,dc=com")
                .setBindPassword("password")
                .setUserFilter("(|(&(objectClass=organizationalPerson)(uid={0}))(&(objectClass=inetOrgPerson)(uid={0})))")
                .setGroupFilter("(|(&(cn=mathematicians)(objectClass=groupOfUniqueNames)(uniqueMember={0}))(&(cn=dit_prodref_pubbles)(objectClass=groupOfUniqueNames)(uniqueMember={0})))")
                .createLdapConfig();

        assertTrue(ldapAuthenticateService.authenticate("euclid", "password", ldapConfig));
    }

}
