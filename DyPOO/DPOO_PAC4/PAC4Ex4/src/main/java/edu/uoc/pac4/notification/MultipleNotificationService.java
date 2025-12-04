package edu.uoc.pac4.notification;

public class MultipleNotificationService implements Notifier {

    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending Email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }

    @Override
    public void sendSms(String phoneNumber, String message) {
        System.out.println("Sending SMS to: " + phoneNumber);
        System.out.println("Message: " + message);
    }

}
