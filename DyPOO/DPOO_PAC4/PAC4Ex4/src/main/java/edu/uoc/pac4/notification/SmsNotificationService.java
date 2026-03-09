package edu.uoc.pac4.notification;

public class SmsNotificationService implements SmsNotifier{
    @Override
    public void sendSms(String phoneNumber, String message) {
        System.out.println("Sending SMS to: " + phoneNumber);
        System.out.println("Message: " + message);
    }
}
