package edu.uoc.pac4.notification;

public class EmailNotificationService implements EmailNotifier {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending Email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}
