package edu.uoc.pac4.notification;

public interface EmailNotifier {
    void sendEmail(String to, String subject, String body);
}
