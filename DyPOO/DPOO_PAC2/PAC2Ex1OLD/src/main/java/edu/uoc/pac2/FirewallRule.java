package edu.uoc.pac2;

import java.time.LocalDate;

public class FirewallRule {
    String ruleName;
    String sourceIP;
    String destinationIP;
    String protocol;
    int port;
    String action;
    boolean isEnabled;
    LocalDate creationDate;
    LocalDate validUntil;

    int minPort = 1;
    int maxPort = 65535;
    int ftpPort = 21;
    int sshPort = 22;
    int httpPort = 80;
    int httpsPort = 443;
    String[] validProtocols = {"TCP", "UDP", "ICMP"};
    String[] validActions = {"ALLOW", "DENY"};

    public FirewallRule(String ruleName, String sourceIP, String destinationIP,
                        String protocol, int port, String action,
                        boolean isEnabled, LocalDate creationDate,
                        LocalDate validUntil) {
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
        this.ruleName = ruleName;
    }
    public String getSourceIP() {
        return sourceIP;
    }
    boolean isValidIP(String ip){
        return false;
    }
    public void setSourceIP(String sourceIP) {
        if (isValidIP(sourceIP)) {
            this.sourceIP = sourceIP;
        }
    }
    public String getDestinationIP() {
        return destinationIP;
    }
    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }
    public String getProtocol() {
        return protocol;
    }
    boolean isValidProtocol(String protocol){
        for (String validProtocol : validProtocols) {
            if (validProtocol.equals(protocol)) {
                return true;
            }
        }
        return false;
    }
    public void setProtocol(String protocol) {
        if (isValidProtocol(protocol)) {
            this.protocol = protocol;
        }
    }
    public int getPort() {
        return port;
    }
    boolean isValidPort(int port){
        return (port >= minPort) && (port <= maxPort);
    }
    public void setPort(int port) {
        if (isValidPort(port)) {
            this.port = port;
        }
    }
    public String getAction() {
        return action;
    }
    boolean isValidAction(String action){
        for (String validAction : validActions) {
            if (validAction.equals(action)) {
                return true;
            }
        }
        return false;
    }
    public void setAction(String action) {
        if (isValidAction(action)) {
            this.action = action;
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
        this.creationDate = creationDate;
    }
    public LocalDate getValidUntil() {
        return validUntil;
    }
    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }
    public boolean isNotExpired() {
        return validUntil.isBefore(LocalDate.now());
    }
    public boolean isCriticalPort() {
        return false;
    }
    public boolean isCriticalRule() {
        return false;
    }
    public boolean isApplicable(String sourceIP, String destinationIP,
                                String protocol, int port) {
        return (isValidIP(sourceIP) && isValidIP(destinationIP)
                && isValidProtocol(protocol) && isValidPort(port));
    }
    public int countMatchingRulesByAction(String[] sourceIP,
                                          String[] destinationIP,
                                          String[] protocol, int[] port,
                                          String action) {
        int count = 0;
        return count;
    }
    boolean areInputsValid (String[] sourceIP, String[] destinationIP,
                            String[] protocol, int[] port) {
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
            if (!isValidPort(validPort)) {
                return false;
            }
        }
       return true;
    }
}
public double calculateAllowedTrafficPct(String[] sourceIP,
                                         String[] destinationIP,
                                         String[] protocol, int[] port) {
   double allowedTrafficPct = 0.0;
    return  allowedTrafficPct;
}
public double calculateDeniedTrafficPct(String[] sourceIP,
                                         String[] destinationIP,
                                         String[] protocol, int[] port) {
    double deniedTrafficPct = 0.0;
    return  deniedTrafficPct;
}
