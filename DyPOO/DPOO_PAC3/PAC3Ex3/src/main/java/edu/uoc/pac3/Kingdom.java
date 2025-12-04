package edu.uoc.pac3;

import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Kingdom {
    private final static int MIN_NAME_LENGTH = 3;
    private final static int MAX_NAME_LENGTH = 20;
    private final static int CODE_LENGTH = 3;
    private final static double MIN_TREASURY = -100000.0;

    public final static String INVALID_NAME = "[ERROR] Kingdom name must have" +
            " between 3 and 20 characters";
    public final static String INVALID_CODE = "[ERROR] Kingdom code must have" +
            " exactly 3 uppercase letters";
    public final static String INVALID_LAST_WAR_DATE = "[ERROR] Last war date" +
            " cannot be in the future";
    public final static String INVALID_TREASURY = "[ERROR] Treasury cannot be" +
            " less than -100000.0";
    public final static String KINGDOM_FULL = "[ERROR] The number of castles " +
            "cannot exceed the maximum";
    public final static String CASTLE_NULL = "[ERROR] The castle cannot be null";
    public final static String CASTLE_ALREADY_EXISTS = "[ERROR] The castle " +
            "already exists in the kingdom";
    public final static String CASTLE_NOT_FOUND = "[ERROR] The castle does " +
            "not exist in the kingdom";

    private final int MAX_CASTLES;
    private String name;
    private String code;
    private LocalDate lastWarDate;
    private double treasury;
    private boolean isReligious;
    private int numCastles;
    private double totalArea;
    private Castle[] castles;

    public Kingdom(String name, String code, LocalDate lastWarDate,
                   double treasury, boolean isReligious, int MAX_CASTLES) throws IllegalArgumentException {
        this.MAX_CASTLES = MAX_CASTLES;
        setName(name);
        setCode(code);
        setLastWarDate(lastWarDate);
        setTreasury(treasury);
        setIsReligious(isReligious);
        numCastles = 0;
        totalArea = 0.0;
        castles = new Castle[this.MAX_CASTLES];
    }

    private void setName(String name) throws IllegalArgumentException {
        if (name == null)
            throw new IllegalArgumentException(INVALID_NAME);
        String trim = name.trim();
        if (trim.length() < MIN_NAME_LENGTH || trim.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(INVALID_NAME);
        this.name = trim;
    }

    public String getName() {
        return name;
    }

    private void setCode(String code) throws IllegalArgumentException {
        if (code == null || code.length() != CODE_LENGTH || !code.matches( "^[A-Z]+$"))
            throw new IllegalArgumentException(INVALID_CODE);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private void setLastWarDate(LocalDate lastWarDate) throws IllegalArgumentException {
        if  (lastWarDate != null && lastWarDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException(INVALID_LAST_WAR_DATE);
        this.lastWarDate = lastWarDate;
    }

    public LocalDate getLastWarDate() {
        return lastWarDate;
    }

    private void setTreasury(double treasury) throws IllegalArgumentException {
       if (treasury < MIN_TREASURY)
           throw new IllegalArgumentException(INVALID_TREASURY);
        this.treasury = treasury;
    }

    public double getTreasury() {
        return treasury;
    }

    private void setIsReligious(boolean isReligious){
        this.isReligious = isReligious;
    }

    public boolean isReligious(){
        return isReligious;
    }

    public long getDaysWithoutWar()
    {
        if (lastWarDate == null)
           return Integer.MAX_VALUE;
        ChronoUnit chronoUnit = ChronoUnit.DAYS;
        return chronoUnit.between(lastWarDate, LocalDate.now());
    }

    public int getMaxCastles() {
        return MAX_CASTLES;
    }

    public Castle[] getCastles() {
        return castles;
    }

    public int getNumCastles() {
        return numCastles;
    }

    // This is wrong
    private int findCastle(Castle castle) throws NullPointerException {
        if (castle == null)
            throw new NullPointerException(CASTLE_NULL);
        for (int i = 0; i < MAX_CASTLES; i++) {
            if (castles[i] != null && castles[i].getId() == castle.getId())
                return i;
        }
        return -1;
    }

    private int findFirstEmptySlot(){
        for (int i = 0; i < MAX_CASTLES; i++) {
            if (castles[i] == null)
                return i;
        }
        return -1;
    }

    public boolean containsCastle(Castle castle) throws NullPointerException {
        if (castle == null)
            throw new NullPointerException(CASTLE_NULL);
        return findCastle(castle) >= 0;
    }

    public void addCastle(Castle castle) throws IllegalStateException,
            NullPointerException, IllegalArgumentException {
        if (numCastles == MAX_CASTLES)
            throw new IllegalStateException(KINGDOM_FULL);
        if (castle == null)
            throw new NullPointerException(CASTLE_NULL);
        if  (containsCastle(castle))
            throw new IllegalArgumentException(CASTLE_ALREADY_EXISTS);
        int firstEmptySlot = findFirstEmptySlot();
        if (firstEmptySlot == -1)
            throw new IllegalStateException(KINGDOM_FULL);
        castles[firstEmptySlot] = castle;
        numCastles++;
    }

    public void removeCastle(Castle castle) throws NullPointerException, IllegalStateException {
        if  (castle == null)
            throw new NullPointerException(CASTLE_NULL);
        if (!containsCastle(castle)) {
            throw new IllegalArgumentException(CASTLE_NOT_FOUND);
        }
        int castlePosition = findCastle(castle);
        castles[castlePosition] = null;
        numCastles--;
    }

    public double getAverageArea() {
        double averageArea = 0.0;
        for (int i = 0; i < MAX_CASTLES; i++) {
            if (castles[i] != null)
                averageArea += castles[i].getArea();
        }
        totalArea = averageArea;
        if (totalArea > 0)
            return totalArea / (double) numCastles;
        else
            return 0.0;
    }
}
