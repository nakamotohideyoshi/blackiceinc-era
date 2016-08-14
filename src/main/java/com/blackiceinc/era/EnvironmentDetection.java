package com.blackiceinc.era;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EnvironmentDetection {

    private static final String IP_DEV_ERA = "107.170.230.231";
    private static final String IP_ONSITE = "10.50.143.8";
    private static final String IP_PRODUCTION = "10.60.9.7";
    private static final String IP_RECOVERY = "10.50.10.7";

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

            String profile = getProfileByIpAddress(hostAddress);

            log.info("Profile : {}, applies to host address : {}", profile, hostAddress);
            return profile;
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Unable to detect environment", e);
        }
    }

    private static String getProfileByIpAddress(String hostAddress) {
        String profile;
        switch (hostAddress) {
            case IP_DEV_ERA:
                profile = "dev_era";
                break;
            case IP_ONSITE:
                profile = "onsite";
                break;
            case IP_PRODUCTION:
                profile = "production";
                break;
            case IP_RECOVERY:
                profile = "recovery";
                break;
            default:
                profile = "local";
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
