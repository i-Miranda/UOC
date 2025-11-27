package edu.uoc.pac2;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class FirewallRule {
    private String ruleName;
    private String sourceIP;
    private String destinationIP;
    private String protocol;
    private int port;
    private String action;
    private boolean isEnabled;
    private LocalDate creationDate;
    private LocalDate validUntil;

    private static int minPort = 1;
    private static int maxPort = 65535;
    private static int ftpPort = 21;
    private static int sshPort = 22;
    private static int httpPort = 80;
    private static int httpsPort = 443;
    private static String[] validProtocols = {"TCP", "UDP", "ICMP"};
    private static String[] validActions = {"ALLOW", "DENY"};

    public FirewallRule(String ruleName, String sourceIP, String destinationIP, String protocol, int port, String action, boolean isEnabled, LocalDate creationDate, LocalDate validUntil) {
        setRuleName(ruleName);
        setSourceIP(sourceIP);
        setDestinationIP(destinationIP);
        setProtocol(protocol);
        setPort(port);
        setAction(action);
        setEnabled(isEnabled);
        setCreationDate(creationDate);
        setValidUntil(validUntil);
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        if (ruleName == null || ruleName.isBlank()) {
            System.out.println("[ERROR] Rule name cannot be null, empty or contain only whitespace.");
        } else {
            this.ruleName = ruleName.trim();
        }
    }

    public String getSourceIP() {
        return sourceIP;
    }

    private boolean isValidIP(String ip) {
        if (ip == null || ip.isBlank()) {
            return false;
        }
        String zeroTo999 = "([0-9]|[1-9][0-9]|[1-9][0-9][0-9])";
        Pattern pattern = Pattern.compile(zeroTo999 + "\\." + zeroTo999 + "\\." + zeroTo999 + "\\." + zeroTo999);
        return pattern.matcher(ip).matches();
    }

    public void setSourceIP(String sourceIP) {
        if (isValidIP(sourceIP)) {
            this.sourceIP = sourceIP;
        } else {
            System.out.println("[ERROR] Source IP must be a valid IPv4 address.");
        }
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public void setDestinationIP(String destinationIP) {
        if (isValidIP(destinationIP)) {
            this.destinationIP = destinationIP;
        } else {
            System.out.println("[ERROR] Destination IP must be a valid IPv4 address.");
        }
    }

    public String getProtocol() {
        return protocol;
    }

    private boolean isValidProtocol(String protocol) {
        if (protocol == null) {
            return false;
        }
        for (String validProtocol : validProtocols) {
            if (validProtocol.equals(protocol.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public void setProtocol(String protocol) {
        if (isValidProtocol(protocol)) {
            this.protocol = protocol.toUpperCase();
        } else {
            System.out.println("[ERROR] Protocol must be one of: TCP, UDP, ICMP.");
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        if (port < minPort || port > maxPort) {
            System.out.println("[ERROR] The port number must be greater than or equal to 1 and less than or equal to 65535.");
        } else {
            this.port = port;
        }
    }

    public String getAction() {
        return action;
    }

    private boolean isValidAction(String action) {
        if (action == null || action.isBlank()) {
            return false;
        }
        for (String validAction : validActions) {
            if (validAction.equals(action.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public void setAction(String action) {
        if (isValidAction(action)) {
            this.action = action.toUpperCase();
        } else {
            System.out.println("[ERROR] Action must be one of: ALLOW, DENY.");
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        if (creationDate == null || creationDate.isAfter(LocalDate.now())) {
            System.out.println("[ERROR] Creation date cannot be null or in the future.");
        } else {
            this.creationDate = creationDate;
        }
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        if (getCreationDate() == null || validUntil == null || validUntil.isBefore(getCreationDate())) {
            System.out.println("[ERROR] Valid until date cannot be null or before the creation date.");
        } else {
            this.validUntil = validUntil;
        }
    }

    public boolean isNotExpired() {
        return getValidUntil().isEqual(LocalDate.now()) || getValidUntil().isAfter(LocalDate.now());
    }

    public boolean isCriticalPort() {
        return getPort() == ftpPort || getPort() == sshPort || getPort() == httpPort || getPort() == httpsPort;
    }

    public boolean isCriticalRule() {
        return isEnabled() && isNotExpired() && isCriticalPort();
    }

    public boolean isApplicable(String sourceIP, String destinationIP, String protocol, int port) {
        return (isEnabled() && isNotExpired() && getSourceIP().equals(sourceIP)
                && getDestinationIP().equals(destinationIP) && getProtocol().equals(protocol) && getPort() == port);
    }

    public int countMatchingRulesByAction(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port, String action) {
        int count = 0;
        if (action == null || getAction() == null || !getAction().equals(action)) {
            return count;
        }
        if (sourceIP == null || destinationIP == null || protocol == null || port == null) {
            return count;
        }
        if (sourceIP.length != destinationIP.length || destinationIP.length != protocol.length || protocol.length != port.length) {
            return count;
        }
        for (int index = 0; index < sourceIP.length; index++) {
            if (!isValidIP(sourceIP[index]) || !isValidIP(destinationIP[index]) || !isValidProtocol(protocol[index])
                    || port[index] < minPort || port[index] > maxPort) {
                continue;
            }
            if (isApplicable(sourceIP[index], destinationIP[index], protocol[index], port[index])) {
                count++;
            }
        }
        return count;
    }

    private boolean areInputsValid(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port) {
        if (sourceIP == null || destinationIP == null || protocol == null || port == null) {
            return false;
        }
        if (sourceIP.length != destinationIP.length || destinationIP.length != protocol.length || protocol.length != port.length) {
            return false;
        }
        for (String validSource : sourceIP) {
            if (!isValidIP(validSource)) {
                return false;
            }
        }
        for (String validDestination : destinationIP) {
            if (!isValidIP(validDestination)) {
                return false;
            }
        }
        for (String validProtocol : protocol) {
            if (!isValidProtocol(validProtocol)) {
                return false;
            }
        }
        for (int validPort : port) {
            if (validPort < minPort || validPort > maxPort) {
                return false;
            }
        }
        return true;
    }

    public double calculateAllowedTrafficPct(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port) {
        if (!areInputsValid(sourceIP, destinationIP, protocol, port)) {
            System.out.println("[ERROR] Invalid input data for traffic " +
                    "percentage calculation.");
            return 0.0;
        }
        int totalPossible = sourceIP.length;
        if (totalPossible == 0) {
            return 0.0;
        }
        return (double) countMatchingRulesByAction(sourceIP, destinationIP,
                protocol, port, "ALLOW") / totalPossible;
    }

    public double calculateDeniedTrafficPct(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port) {
        if (!areInputsValid(sourceIP, destinationIP, protocol, port)) {
            System.out.println("[ERROR] Invalid input data for traffic " +
                    "percentage calculation.");
            return 0.0;
        }
        int totalPossible = sourceIP.length;
        if (totalPossible == 0) {
            return 0.0;
        }
        return  (double) countMatchingRulesByAction(sourceIP, destinationIP,
                protocol, port, "DENY") / totalPossible;
    }
}