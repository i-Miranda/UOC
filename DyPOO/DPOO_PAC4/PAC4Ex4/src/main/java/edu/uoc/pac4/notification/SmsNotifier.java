package edu.uoc.pac4.notification;

public interface SmsNotifier {
    void sendSms(String phoneNumber, String message);
}
