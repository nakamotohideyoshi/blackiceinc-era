package com.blackiceinc.era.web;

import com.blackiceinc.era.services.security.EraAuthenticationProvider;
import com.blackiceinc.era.services.security.LdapUserBindAuthenticateService;
import com.blackiceinc.era.services.security.model.LdapConfig;
import com.blackiceinc.era.services.security.model.LdapConfigBuilder;
import com.blackiceinc.era.web.model.LdapAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class LdapClientController {

    @Autowired
    private LdapUserBindAuthenticateService ldapUserBindAuthenticateService;

    @Autowired
    private EraAuthenticationProvider eraAuthenticationProvider;

    @RequestMapping(value = "/ldapClient", method = RequestMethod.GET)
    public String ldapClient(@RequestParam(value = "domain", required = false, defaultValue = "") String domain, HttpServletRequest request, HttpServletResponse response, Model model) {

        LdapAuth ldapAuth;
        switch (domain) {
            case "north": {
                ldapAuth = getNorthLdapAuth();
                break;
            }
            case "south": {
                ldapAuth = getSouthLdapAuth();
                break;
            }
            default: {
                ldapAuth = new LdapAuth("euclid", "password", getSimpleLdapConfig());
            }
        }

        model.addAttribute("ldapAuth", ldapAuth);

        return "ldapClient";
    }

    @RequestMapping(value = "/ldapClient", method = RequestMethod.POST)
    public String ldapClient(@ModelAttribute("ldapAuth") LdapAuth ldapAuth, HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("ldapAuth", ldapAuth);

        boolean authenticate = ldapUserBindAuthenticateService.authenticate(ldapAuth.getUsername(), ldapAuth.getPassword(), ldapAuth.getLdapConfig());
        model.addAttribute("result", authenticate);

        return "ldapClient";
    }

    private LdapConfig getSimpleLdapConfig() {
        ArrayList<String> servers = new ArrayList<>();
        servers.add("ldap.forumsys.com");
        return new LdapConfigBuilder()
                .setLdapServers(servers)
                .setLdapServerPort(389).setBaseDn("dc=example,dc=com")
                .setBindDn("cn=read-only-admin,dc=example,dc=com")
                .setBindPassword("password")
                .setUserFilter("(|(&(objectClass=organizationalPerson)(uid={0}))(&(objectClass=inetOrgPerson)(uid={0})))")
                .setGroupFilter("(|(&(cn=mathematicians)(objectClass=groupOfUniqueNames)(uniqueMember={0}))(&(cn=dit_prodref_pubbles)(objectClass=groupOfUniqueNames)(uniqueMember={0})))")
                .createLdapConfig();
    }

    public LdapAuth getNorthLdapAuth() {
        return new LdapAuth("ldapbasel2.dev", "kkkKKK234", eraAuthenticationProvider.getNorthVibCorpConfig());
    }

    public LdapAuth getSouthLdapAuth() {
        return new LdapAuth("ldapbasel2.devs", "kkkKKK234", eraAuthenticationProvider.getSouthVibCorpConfig());
    }
}
