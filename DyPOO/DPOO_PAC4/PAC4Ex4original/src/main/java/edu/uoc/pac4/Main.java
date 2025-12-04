package edu.uoc.pac4;

import edu.uoc.pac4.notification.*;

public class Main {

    public static void main(String[] args) {
        Notifier notifier = new MultipleNotificationService();

        System.out.println("=== Testing Email Notification ===");
        notifier.sendEmail("user@example.com", "Hello!", "This is a test email.");

        System.out.println("\n=== Testing SMS Notification ===");
        notifier.sendSms("+123456789", "This is a test SMS.");

        System.out.println("\n=== Testing Combined Email + SMS Notification ===");
        notifier.sendEmail("combo@example.com", "Combined Email", "Email sent from multi-service.");
        notifier.sendSms("+987654321", "SMS sent from multi-service.");
    }

}
