package edu.uoc.pac3;

import java.time.LocalDate;
import java.util.Locale;

public class Castle {
    private final static int MIN_NAME_LENGTH = 3;
    private final static double MIN_AREA = 100.0;
    private final static LocalDate MIDDLE_AGES_START = LocalDate.of(500, 1, 1);
    private final static LocalDate MIDDLE_AGES_END = LocalDate.of(1499, 12, 31);
    private final static int MIN_SYMBOLIC_ARTIFACT_LENGTH = 2;
    public final static String INVALID_NAME = "[ERROR] Castle name must have " +
            "at least 3 characters";
    public final static String INVALID_CONSTRUCTION_DATE = "[ERROR] " +
            "Construction date cannot be null and must be between 0500-01-01 and 1499-12-31";
    public final static String INVALID_AREA = "[ERROR] Area must be at " +
            "least 100.0 square meters";
    public final static String INVALID_SYMBOLIC_ARTIFACT = "[ERROR] Symbolic " +
            "artifact cannot be null, formed by only whitespace and must have" +
            " at least 2 characters";

    private String name;
    private LocalDate constructionDate;
    private double area;
    private String symbolicArtifact;

    private Keep keep;

    public Castle(String name, LocalDate constructionDate, double area,
            String symbolicArtifact) throws IllegalArgumentException {
        this.setName(name);
        this.setConstructionDate(constructionDate);
        this.setArea(area);
        this.setSymbolicArtifact(symbolicArtifact);
        keep = null;
    }

    public Castle(String name, LocalDate constructionDate, double area,
                  String symbolicArtifact, String keepLordName,
                  int keepFloors, double keepHeight, int keepNumGuards,
                  boolean keepHasDungeon) throws IllegalArgumentException {
        setName(name);
        setConstructionDate(constructionDate);
        setArea(area);
        setSymbolicArtifact(symbolicArtifact);
        try {
            keep = new Keep(keepLordName, keepFloors, keepHeight, keepNumGuards,
                    keepHasDungeon);
        } catch (IllegalArgumentException e) {
            keep = null;
        }
    }

    private void setName(String name) throws IllegalArgumentException {
        if (name == null || name.trim().length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    private void setConstructionDate(LocalDate constructionDate) throws IllegalArgumentException {
        if (constructionDate == null || (constructionDate.isBefore(MIDDLE_AGES_START) || constructionDate.isAfter(MIDDLE_AGES_END))) {
            throw new IllegalArgumentException(INVALID_CONSTRUCTION_DATE);
        }
        this.constructionDate = constructionDate;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    private void setArea(double area) throws IllegalArgumentException {
        if (area < MIN_AREA) {
            throw new IllegalArgumentException(INVALID_AREA);
        }
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    private void setSymbolicArtifact(String symbolicArtifact) throws IllegalArgumentException {
        if (symbolicArtifact == null || symbolicArtifact.trim().length() < MIN_SYMBOLIC_ARTIFACT_LENGTH) {
            throw new IllegalArgumentException(INVALID_SYMBOLIC_ARTIFACT);
        }
        String trim = symbolicArtifact.trim();
        this.symbolicArtifact =
                trim.substring(0, 1).toUpperCase() + trim.substring(1).toLowerCase();
    }

    public String getSymbolicArtifact() {
        return symbolicArtifact;
    }

    private void setKeep(String lordName, int floors, double height,
                       int numGuards,
            boolean hasDungeon) {
        keep = new Keep(lordName, floors, height, numGuards, hasDungeon);
    }

    public Keep getKeep() {
        return keep;
    };
}
