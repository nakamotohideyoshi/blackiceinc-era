package com.blackiceinc.era;

import com.blackiceinc.era.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EnvironmentDetection {

    private static final String IP_ONSITE = "10.50.143.8";
    private static final String IP_PRODUCTION = "10.60.9.7";
    private static final String IP_RECOVERY = "10.50.10.7";

    private static final String HOST_ERA_BLACKICEINC_COM = "era.blackiceinc.com";

    private static final Logger log = LoggerFactory.getLogger(EnvironmentDetection.class);

    private EnvironmentDetection() {
    }

    static String detectEnvironmentProfile() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            log.info("Detected host name : {} with ip address : {}", hostName, hostAddress);
            if (classExists("com.gargoylesoftware.htmlunit.WebClient")) {
                if ("127.0.0.1".equals(hostAddress)) {
                    return "test-ci";
                }
                return "test-local";
            }

            String profile = getProfileByIpAddress(hostName, hostAddress);

            log.info("Profile : {}, applies to host name : {} and address : {}", profile, hostName, hostAddress);
            return profile;
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Unable to detect environment", e);
        }
    }

    private static String getProfileByIpAddress(String hostName, String hostAddress) {
        String profile;

        if (hostName.equals(HOST_ERA_BLACKICEINC_COM)) {
            profile = Constants.SPRING_PROFILE_DEV_ERA;
        } else if (IP_ONSITE.equals(hostAddress)) {
            profile = Constants.SPRING_PROFILE_ONSITE;
        } else if (IP_PRODUCTION.equals(hostAddress)) {
            profile = Constants.SPRING_PROFILE_PRODUCTION;
        } else if (IP_RECOVERY.equals(hostAddress)) {
            profile = Constants.SPRING_PROFILE_RECOVERY;
        } else {
            profile = Constants.SPRING_PROFILE_LOCAL;
        }

        return profile;
    }

    private static boolean classExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
