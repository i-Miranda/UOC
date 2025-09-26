package edu.uoc.pac1;

public class PAC1Ex2 {

    // Area of each zone of the spaceship hull (in m²)
    private static final double[] ZONES_AREAS = {4.0, 2.5, 5.8, 3.2, 6.5, 4.9, 3.0, 7.1};

    // Maximum allowed impact density before requiring urgent repairs
    private static final double MAX_IMPACT_DENSITY = 0.25;

    public static double getZoneArea(int zoneIndex) {
        //TODO
    }

    public static double calculateImpactDensity(int impacts, int zoneIndex) {
        //TODO
    }

    public static boolean needsUrgentRepair(int impacts, int zoneIndex) {
        //TODO
    }

    public static int calculateTotalImpacts(int[] impactsArray) {
        //TODO
    }

    public static int getMostDamagedZone(int[] impactsArray) {
        //TODO
    }

    public static void printDamageReport(String[] zoneNames, int[] impactsArray) {
        //TODO
    }

}
