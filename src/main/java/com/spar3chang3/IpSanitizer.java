package com.spar3chang3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpSanitizer {
    private static final String IP_REGEX = "\\b(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])\\b";
    //Got this from Gemini, supposedly it breaks down ip addresses into 4 octets, but I'm not seasoned enough in regex to tell if this is good enough.
    //Any passerbys, please pull request if this is incorrect :praying_emoji:

    private static Pattern ipPattern = Pattern.compile(IP_REGEX);

    private static Matcher ipMatcher;

    public static String removeIp(String input) {
        ipMatcher = ipPattern.matcher(input);
        return ipMatcher.replaceAll("[REDACTED-IP]");
    }

    public static String sanitizeIp(String input) {
        ipMatcher = ipPattern.matcher(input);
        if (ipMatcher.group().contains("192.168")) {
            return input;
        } else {
            return ipMatcher.replaceFirst("[REDACTED-IP]");
        }
    }
}
