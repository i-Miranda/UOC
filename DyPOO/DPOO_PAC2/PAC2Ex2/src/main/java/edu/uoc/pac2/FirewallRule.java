package edu.uoc.pac2;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

/**
 *  This class embodies a firewall rule
 * @author Ivan Miranda Moral
 * @version 1.0
 */
public class FirewallRule {

    /**
     * The rule's name.
     */
    private String ruleName;

    /**
     * The source IP address.
     */
    private String sourceIP;

    /**
     * The destination IP address.
     */
    private String destinationIP;

    /**
     * The protocol. Must be one of the strings found in validProtocols.
     */
    private String protocol;

    /**
     * The port. Must be between/equal to minPort and/or maxPort.
     */
    private int port;

    /**
     * The action. Must be one of the strings found in validProtocols.
      */
    private String action;

    /**
     * Defines if the rule is enabled or not.
      */
    private boolean isEnabled;

    /**
     * The date created. Cannot be a date in the future or newer than
     * validUntil.
      */
    private LocalDate creationDate;

    /**
     * The expiration date. Cannot be older than creationDate.
      */
    private LocalDate validUntil;

    /**
     * The static constant that defines the min value for the port int.
      */
    private static final int minPort = 1;

    /**
     * The static constant that defines the max value for the port int.
     */
    private static final int maxPort = 65535;

    /**
     * The static constant that defines the value for the port used in ftp
      */
    private static final int ftpPort = 21;

    /**
     * The static constant that defines the value for the port used in ssh
     */
    private static final int sshPort = 22;

    /**
     * The static constant that defines the value for the port used in http
     */
    private static final int httpPort = 80;

    /**
     * The static constant that defines the value for the port used in https
     */
    private static final int httpsPort = 443;

    /**
     * The static constant list of valid protocols
      */
    private static final String[] validProtocols = {"TCP", "UDP", "ICMP"};

    /**
     * The static constant list of valid actions
     */
    private static final String[] validActions = {"ALLOW", "DENY"};

    /**
     * The FireWallRule constructor
     * @param ruleName The FireWallRule's name.
     * @param sourceIP The FireWallRules's source IP
     * @param destinationIP The FireWallRule's destination IP
     * @param protocol The FireWallRule's protocol
     * @param port The FireWallRule's port
     * @param action The FireWallRule's action
     * @param isEnabled Whether the FireWallRule is enabled or not
     * @param creationDate The date the FireWallRule was created
     * @param validUntil The FireWallRule's expiration date
     * @throws Exception When any of the setters throw an exception
     */
    public FirewallRule(String ruleName, String sourceIP, String destinationIP,
                        String protocol, int port, String action,
                        boolean isEnabled, LocalDate creationDate,
                        LocalDate validUntil) throws Exception {
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

    /**
     * The ruleName getter
     * @return the FireWallRule rule name
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * The ruleName setter
     * @param ruleName The FireWallRule's name.
     * @throws Exception if ruleName is null empty or blank
     */
    public void setRuleName(String ruleName) throws Exception {
        if (ruleName == null || ruleName.isBlank()) {
           throw new Exception("[ERROR] Rule name cannot be null, empty or contain only whitespace.");
        }
        this.ruleName = ruleName.trim();
    }

    /**
     * The sourceIP getter
     * @return The FireWallRule's name.
     */
    public String getSourceIP() {
        return sourceIP;
    }

    /**
     * Checks if a string is an ip address using regex
     * @param ip should be an ip address
     * @return false if the string is not in the correct format of an ip address
     */
    private boolean isValidIP(String ip) {
        if (ip == null || ip.isBlank()) {
            return false;
        }
        String zeroTo999 = "([0-9]|[1-9][0-9]|[1-9][0-9][0-9])";
        Pattern pattern = Pattern.compile(zeroTo999 + "\\." + zeroTo999 + "\\." + zeroTo999 + "\\." + zeroTo999);
        return pattern.matcher(ip).matches();
    }

    /**
     * The sourceIP setter
     * @param sourceIP The FireWallRules's source IP
     * @throws Exception if sourceIP is not a valid IPV4 address
     */
    public void setSourceIP(String sourceIP) throws Exception{
        if (!isValidIP(sourceIP)) {
            throw new Exception("[ERROR] Source IP must be a valid IPv4 address.");
        }
        this.sourceIP = sourceIP;
    }

    /**
     * The destinationIP getter
     * @return The FirewWallRule's destination IP
     */
    public String getDestinationIP() {
        return destinationIP;
    }

    /**
     * The destinationIP setter
     * @param destinationIP The FireWallRule's destination IP
     * @throws Exception if destinationIP is not a valid IPV4 address
     */
    public void setDestinationIP(String destinationIP) throws Exception{
        if (!isValidIP(destinationIP)) {
            throw new Exception("[ERROR] Destination IP must be a valid IPv4 address.");
        }
        this.destinationIP = destinationIP;
    }

    /**
     * The protocol getter
     * @return The FireWallRule's protocol.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Checks if the string is a valid protocol
     * @param protocol The FireWallRule's protocol
     * @return false if the protocol string does not match any of the strings
     * in validProtocols array
     */
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

    /**
     * The protocol setter
     * @param protocol The FireWallRule's protocol
     * @throws Exception if protocol is not a valid protocol
     */
    public void setProtocol(String protocol) throws Exception {
        if (!isValidProtocol(protocol)) {
            throw new Exception("[ERROR] Protocol must be one of: TCP, UDP, ICMP.");
        }
        this.protocol = protocol.toUpperCase();
    }

    /**
     * The port getter
     * @return The FireWallRule's port
     */
    public int getPort() {
        return port;
    }

    /**
     * The port setter
     * @param port The FireWallRule's port
     * @throws Exception if the port is not within the range of minPort and
     * maxPort
     */
    public void setPort(int port) throws Exception {
        if (port < minPort || port > maxPort) {
            throw new Exception("[ERROR] The port number must be greater than or equal to 1 and less than or equal to 65535.");
        }
        this.port = port;
    }

    /**
     * The action getter
     * @return The FireWallRule's action
     */
    public String getAction() {
        return action;
    }

    /**
     * Checks if the string is a valid action
     * @param action The FireWallRule's action
     * @return false if the action string does not equal any of the strings
     * in the validActions array
     */
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

    /**
     * The action setter
     * @param action The FireWallRule's action
     * @throws Exception if action is not a valid action
     */
    public void setAction(String action) throws Exception{
        if (!isValidAction(action)) {
            throw new Exception("[ERROR] Action must be one of: ALLOW, DENY.");
        }
        this.action = action.toUpperCase();
    }

    /**
     * Checks if the FireWallRule is enabled
     * @return The FireWallRule's isEnabled
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * The isEnabled setter
     * @param isEnabled Whether the FireWallRule is enabled or not
     */
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * The creationDate getter
     * @return  The FireWallRule's creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * The creationDate setter
     * @param creationDate The date the FireWallRule was created
     * @throws Exception if the date is null or later than the actual date
     */
    public void setCreationDate(LocalDate creationDate) throws Exception {
        if (creationDate == null || creationDate.isAfter(LocalDate.now())) {
            throw new Exception("[ERROR] Creation date cannot be null or in the future.");
        }
        this.creationDate = creationDate;
    }

    /**
     * The validUntil getter
     * @return The FireWallRule's expiration date
     */
    public LocalDate getValidUntil() {
        return validUntil;
    }

    /**
     * The validUntil setter
     * @param validUntil The FireWallRule's expiration date
     * @throws Exception if the date is null or earlier than the creation date
     */
    public void setValidUntil(LocalDate validUntil) throws Exception {
        if (getCreationDate() == null || validUntil == null || validUntil.isBefore(getCreationDate())) {
            throw new Exception("[ERROR] Valid until date cannot be null or before the creation date.");
        }
        this.validUntil = validUntil;
    }

    /**
     * Checks if the FireWallRule is not expired (returns true if not)
     * @return Whether or not the FireWallRule is expired
     */
    public boolean isNotExpired() {
        return getValidUntil().isEqual(LocalDate.now()) || getValidUntil().isAfter(LocalDate.now());
    }

    /**
     * Checks if the FireWallRule's port is a critical port (ftp, ssh, http,
     * or https.)
     * @return Whether the FireWallRule port is a critical port or not
     */
    public boolean isCriticalPort() {
        return getPort() == ftpPort || getPort() == sshPort || getPort() == httpPort || getPort() == httpsPort;
    }

    /**
     * Checks if the FireWallRule is a critical rule (if it is not expired,
     * uses a critical port and is enabled.)
     * @return The FireWallRule is considered a critical rule
     * (if it is not expired, uses a critical port and is enabled)
     */
    public boolean isCriticalRule() {
        return isEnabled() && isNotExpired() && isCriticalPort();
    }

    /**
     * Checks if rule is applicable
     * @param sourceIP The FireWallRules's source IP
     * @param destinationIP The FireWallRule's destination IP
     * @param protocol The FireWallRule's protocol
     * @param port The FireWallRule's port
     * @return Whether the FireWallRule is applicable or not
     */
    public boolean isApplicable(String sourceIP, String destinationIP, String protocol, int port) {
        return (isEnabled() && isNotExpired() && getSourceIP().equals(sourceIP)
                && getDestinationIP().equals(destinationIP) && getProtocol().equals(protocol) && getPort() == port);
    }

    /**
     * Returns the amount of matching applicable rules if the action equals
     * the FireWallRule action
     * @param sourceIP The FireWallRules's source IP
     * @param destinationIP The FireWallRule's destination IP
     * @param protocol The FireWallRule's protocol
     * @param port The FireWallRule's port
     * @param action The FireWallRule's action
     * @return the amount of matching applicable rules if the action equals
     * the FireWallRule action
     */
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

    /**
     * Returns true if all param arrays are of equal length, and all are valid
     * @param sourceIP The FireWallRules's source IP
     * @param destinationIP The FireWallRule's destination IP
     * @param protocol The FireWallRule's protocol
     * @param port The FireWallRule's port
     * @return Whether all parameters are valid using their corresponding
     * validation functions
     */
    private boolean areInputsValid(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port) {
        if (sourceIP == null ||destinationIP == null || protocol == null || port == null) {
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

    /**
     * Returns the percentage of rules that allow traffic
     * @param sourceIP The FireWallRules's source IP
     * @param destinationIP The FireWallRule's destination IP
     * @param protocol The FireWallRule's protocol
     * @param port The FireWallRule's port
     * @return The percentage of rules that allow traffic
     * @throws Exception if the input data is invalid
     */
    public double calculateAllowedTrafficPct(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port)
            throws Exception{
        if (!areInputsValid(sourceIP, destinationIP, protocol, port)) {
            throw new Exception("[ERROR] Invalid input data for traffic percentage calculation.");
        }
        int totalPossible = sourceIP.length;
        if (totalPossible == 0) {
            return 0.0;
        }
        int matched = countMatchingRulesByAction(sourceIP, destinationIP, protocol, port, "ALLOW");
        return (double) matched / totalPossible;
    }

    /**
     * Returns the percentage of rules that deny traffic
     * @param sourceIP The FireWallRules's source IP
     * @param destinationIP The FireWallRule's destination IP
     * @param protocol The FireWallRule's protocol
     * @param port The FireWallRule's port
     * @return the amount of rules that deny traffic
     * @throws Exception if the input data is invalid
     */
    public double calculateDeniedTrafficPct(String[] sourceIP, String[] destinationIP, String[] protocol, int[] port)
            throws Exception{
        if (!areInputsValid(sourceIP, destinationIP, protocol, port)) {
            throw new Exception("[ERROR] Invalid input data for traffic percentage calculation.");
        }
        int totalPossible = sourceIP.length;
        if (totalPossible == 0) {

            return 0.0;
        }
        int matched = (countMatchingRulesByAction(sourceIP, destinationIP, protocol, port, "DENY"));
        return (double) matched / totalPossible;
    }
}
