package edu.uoc.pac1;

public class PAC1Ex2 {

    // Area of each zone of the spaceship hull (in m²)
    private static final double[] ZONES_AREAS = {4.0, 2.5, 5.8, 3.2, 6.5, 4.9, 3.0, 7.1};

    // Maximum allowed impact density before requiring urgent repairs
    private static final double MAX_IMPACT_DENSITY = 0.25;

    public static double getZoneArea(int zoneIndex) {
        return ZONES_AREAS[zoneIndex];
    }

    public static double calculateImpactDensity(int impacts, int zoneIndex) {
        return (double) impacts / getZoneArea(zoneIndex);
    }

    public static boolean needsUrgentRepair(int impacts, int zoneIndex) {
        return calculateImpactDensity(impacts, zoneIndex) >= MAX_IMPACT_DENSITY;
    }

    public static int calculateTotalImpacts(int[] impactsArray) {
        int totalImpacts = 0;
        int i;

        for (i = 0; i < impactsArray.length; i++) {
            totalImpacts += impactsArray[i];
        }
        return totalImpacts;
    }

    public static int getMostDamagedZone(int[] impactsArray) {
        int mostDamagedZone = 0;
        double currentImpactDensity = 0;
        double mostDamagedZoneImpactDensity = 0;
        int i;

        for (i = 0; i < impactsArray.length; i++) {
            currentImpactDensity = calculateImpactDensity(impactsArray[i], i);
            if (currentImpactDensity > mostDamagedZoneImpactDensity) {
                mostDamagedZone = i;
                mostDamagedZoneImpactDensity = currentImpactDensity;
            }
        }
        return mostDamagedZone;
    }

    public static void printDamageReport(String[] zoneNames, int[] impactsArray) {
        int zonesThatNeedUrgentRepair = 0;
        int i = 0;

        System.out.println("Meteor impact damage report:");
        do {
            System.out.print("\t");
            System.out.print(zoneNames[i]);
            System.out.print(": DENSITY ");
            System.out.print(String.valueOf(Math.round(calculateImpactDensity(impactsArray[i], i) * 1000.0) / 1000.0));
            if (needsUrgentRepair(impactsArray[i], i)) {
                zonesThatNeedUrgentRepair++;
                System.out.println(" (URGENT REPAIR REQUIRED)");
            } else {
                System.out.println(" (Normal)");
            }
            i++;
        } while (i < impactsArray.length);
        System.out.println("Total impacts: " + String.valueOf(calculateTotalImpacts(impactsArray)));
        System.out.println("Most damaged zone: " + String.valueOf(zoneNames[getMostDamagedZone(impactsArray)]));
        System.out.println("Urgent repairs needed in " + String.valueOf(zonesThatNeedUrgentRepair) + " zones.");
    }

}
