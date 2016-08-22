package com.blackiceinc.era;

import com.blackiceinc.era.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EnvironmentDetection {

    private static final Logger log = LoggerFactory.getLogger(EnvironmentDetection.class);

    private static final String HOST_ERA_BLACKICEINC_COM = "era.blackiceinc.com";
    private static final String HOST_DEV_BASEL2_COGNOS = "dev-basel2-cognos";
    private static final String HOST_DC_BASEL2_COGNOS = "dc-basel2-cognos";
    private static final String HOST_DR_BASEL2_COGNOS = "dr-basel2-cognos";

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

            String profile = getProfileByIpAddress(hostName);

            log.info("Profile : {}, applies to host name : {} and address : {}", profile, hostName, hostAddress);
            return profile;
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Unable to detect environment", e);
        }
    }

    private static String getProfileByIpAddress(String hostName) {
        String profile;

        String hostNameLower = hostName.toLowerCase();
        if (hostNameLower.startsWith(HOST_ERA_BLACKICEINC_COM.toLowerCase())) {
            profile = Constants.SPRING_PROFILE_DEV_ERA;
        } else if (hostNameLower.startsWith(HOST_DEV_BASEL2_COGNOS.toLowerCase())) {
            profile = Constants.SPRING_PROFILE_ONSITE;
        } else if (hostNameLower.startsWith(HOST_DC_BASEL2_COGNOS.toLowerCase())) {
            profile = Constants.SPRING_PROFILE_PRODUCTION;
        } else if (hostNameLower.startsWith(HOST_DR_BASEL2_COGNOS.toLowerCase())) {
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
