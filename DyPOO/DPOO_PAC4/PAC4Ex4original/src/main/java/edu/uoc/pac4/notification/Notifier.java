package edu.uoc.pac4.notification;

public interface Notifier {

    void sendEmail(String to, String subject, String body);
    void sendSms(String phoneNumber, String message);

}
