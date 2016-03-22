package com.blackiceinc.era.services.security;

import com.blackiceinc.era.services.security.model.LdapConfig;
import com.unboundid.ldap.sdk.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LdapAuthenticateService {

    private static Logger log = LoggerFactory.getLogger(LdapAuthenticateService.class);

    public boolean authenticate(String username, String password, LdapConfig ldapConfig) {
        boolean success = false;

        LDAPConnection ldapConnection = makeLdapConnection(username, ldapConfig);
        if (ldapConnection != null && ldapConnection.isConnected()) {
            log.info("****** LDAP connection successful *****");

            SearchRequest searchRequest = null;
            try {
                searchRequest = createSearchRequest(username, ldapConfig);

                try {
                    SearchResult searchResult = ldapConnection.search(searchRequest);
                    if (0 == searchResult.getSearchEntries().size()) {
                        log.error("****** User : " + username + " cannot be authenticated *****");
                    } else {
                        for (SearchResultEntry entry : searchResult.getSearchEntries()) {
                            BindRequest bindRequest = new SimpleBindRequest(entry.getDN(), password);
                            String userCN = entry.getDN();
                            BindResult bindResult = ldapConnection.bind(bindRequest);
                            if (bindResult.getResultCode().equals(ResultCode.SUCCESS)) {
                                SearchRequest searchGroupRequest = createSearchGroupRequest(username, userCN, ldapConfig);
                                try {
                                    SearchResult searchGroupResult = ldapConnection.search(searchGroupRequest);
                                    if (0 == searchGroupResult.getSearchEntries().size()) {
                                        log.error("****** User : " + username + " successfully authenticated but not member of at least one allowed group *****");
                                    } else {
                                        for (SearchResultEntry groupEntry : searchResult.getSearchEntries()) {
                                            log.info("******* User : " + username + " successfully authenticated and member of at least one allowed group");
                                            String group = groupEntry.getDN();
                                            log.info("******* DN for the logged In user : " + group + " *********");
                                            success = true;
                                        }
                                    }
                                } catch (LDAPSearchException lse) {
                                    ResultCode resultCode = lse.getResultCode();
                                    String errorMessageFromServer = lse.getMessage();
                                    log.error("***Search result failed in groups:  " + resultCode.toString() + errorMessageFromServer);
                                }
                            }
                            break;
                        }
                    }
                } catch (LDAPSearchException lse) {
                    // The search failed for some reason.
                    ResultCode resultCode = lse.getResultCode();
                    String errorMessageFromServer = lse.getMessage();
                    log.error("***Search result failed :  " + resultCode.toString() + errorMessageFromServer);
                }
            } catch (LDAPException e) {
                logErrorLdapException(username, ldapConfig, ldapConnection.getConnectedAddress(), e);
            }
            ldapConnection.close();
            log.info("****** LDAP connection closed *****");
        } else {
            log.error("****** Unable to establish LDAP connection *****");
        }

        return success;
    }

    private SearchRequest createSearchGroupRequest(String userName, String userCN, LdapConfig ldapConfig) throws LDAPException {
        log.info("******* Authentication for : " + userName + " successful.");
        String groupFilter = ldapConfig.getGroupFilter();
        String exactGroupFilter = StringUtils.replace(groupFilter, "{0}", userCN);
        log.info("****** Filter query for searching in groups : " + exactGroupFilter);
        return new SearchRequest(ldapConfig.getBaseDn(), SearchScope.SUB, exactGroupFilter);
    }

    private SearchRequest createSearchRequest(String username, LdapConfig ldapConfig) throws LDAPException {
        String exactUserFilter = StringUtils.replace(ldapConfig.getUserFilter(), "{0}", username);

        log.info("****** Filter Name / User name : " + exactUserFilter);
        SearchRequest searchRequest = new SearchRequest(ldapConfig.getBaseDn(), SearchScope.SUB, exactUserFilter);
        log.info("****** Search request BaseDN: " + searchRequest.getBaseDN());

        return searchRequest;
    }

    private LDAPConnection makeLdapConnection(String username, LdapConfig ldapConfig) {
        LDAPConnection ldapConnection = null;
        List<String> ldapServers = ldapConfig.getLdapServers();

        for (String ldapServer : ldapServers) {
            try {
                ldapConnection = new LDAPConnection(ldapServer,
                        ldapConfig.getLdapServerPort(),
                        ldapConfig.getBindDn(), ldapConfig.getBindPassword());
                log.info("****** LDAP connection address : " + ldapConnection.getConnectedAddress()
                        + ", connection port : " + ldapConnection.getConnectedPort()
                        + ", connection time : " + ldapConnection.getConnectTime());
            } catch (LDAPException e) {
                logErrorLdapException(username, ldapConfig, ldapServer, e);
            }
            if (ldapConnection != null) {
                break;
            }
        }

        return ldapConnection;
    }

    private void logErrorLdapException(String username, LdapConfig ldapConfig, String ldapServer, LDAPException e) {
        log.error("****** Bind user : " + username
                + " cannot connect to LDAP server running on host "
                + ldapServer + " : "
                + ldapConfig.getLdapServerPort()
                + " with Base Dn : " + ldapConfig.getBaseDn()
                + " ******", e.getExceptionMessage());
    }

}
