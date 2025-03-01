package com.example.samplemenu;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class IPUtils {

    // Regular expression for IPv4 validation
    private static final String IPV4_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern ipv4Pattern = Pattern.compile(IPV4_PATTERN);

    /**
     * Check if the given string is a valid IPv4 address.
     */
    public static boolean isIPv4Address(String ip) {
        return ipv4Pattern.matcher(ip).matches();
    }

    /**
     * Check if the given string is a valid IPv6 address.
     */
    public static boolean isIPv6Address(String ip) {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            return inetAddress.getHostAddress().equals(ip) && ip.contains(":");
        } catch (UnknownHostException e) {
            return false;
        }
    }

    /**
     * Check if the given string is a valid IP address (either IPv4 or IPv6).
     */
    public static boolean isValidIPAddress(String ip) {
        return isIPv4Address(ip) || isIPv6Address(ip);
    }
}

