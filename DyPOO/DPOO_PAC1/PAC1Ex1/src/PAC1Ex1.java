import java.util.Scanner;

public class PAC1Ex1 {
    public static boolean isDataValid(double initialCapital, double annualInterestRate, int periodsPerYear, double timeInYears) {
        boolean isValid = true;

        if (initialCapital <= 0) {
            System.out.println("Initial capital must be greater than zero.");
            isValid = false;
        }

        if (annualInterestRate < 0) {
            System.out.println("Annual interest rate must be greater or equal to zero.");
            isValid = false;
        }

        if (periodsPerYear <= 0) {
            System.out.println("Periods per year must be greater than zero.");
            isValid = false;
        }

        if (timeInYears < 0) {
            System.out.println("Time in years must be greater or equal to zero.");
            isValid = false;
        }

        if (!isValid) {
            System.out.println("Please re-enter the data.");
        }

        return isValid;
    }

    public static double calculateCompoundInterest(double initialCapital, double annualInterestRate, int periodsPerYear, double timeInYears) {
        int totalPeriods = (int) (periodsPerYear * timeInYears);
        double interestPerPeriod = annualInterestRate / periodsPerYear;
        double finalValue = initialCapital;

        for (int i = 0; i < totalPeriods; i += 1) {
            finalValue *= 1 + interestPerPeriod;
        }

        return finalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double initialCapital, annualInterestRate, timeInYears;
        int periodsPerYear;

        do {
            System.out.print("Enter the initial capital (greater than zero): ");
            initialCapital = scanner.nextDouble();

            System.out.print("Enter the annual interest rate (greater or equal to zero): ");
            annualInterestRate = scanner.nextDouble();

            System.out.print("Enter the number of periods per year (greater than zero): ");
            periodsPerYear = scanner.nextInt();

            System.out.print("Enter the time in years (greater or equal to zero): ");
            timeInYears = scanner.nextDouble();
        } while (!isDataValid(initialCapital, annualInterestRate, periodsPerYear, timeInYears));

        double finalCapital = calculateCompoundInterest(initialCapital, annualInterestRate, periodsPerYear, timeInYears);
        System.out.printf("Final capital after %.2f years: %.2f\n", timeInYears,
                finalCapital);

        scanner.close();
    }
}