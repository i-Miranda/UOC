package edu.uoc.pac4.notification;

public class MultipleNotificationService implements Notifier {
    private final EmailNotificationService emailNotificationService;
    private final SmsNotificationService smsNotificationService;

    public MultipleNotificationService() {
        emailNotificationService = new EmailNotificationService();
        smsNotificationService = new SmsNotificationService();
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        emailNotificationService.sendEmail(to, subject, body);
    }

    @Override
    public void sendSms(String phoneNumber, String message) {
        smsNotificationService.sendSms(phoneNumber, message);
    }
}
