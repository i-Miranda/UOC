package edu.uoc.pac4.alien;

public interface ShapeShifter {
    public abstract void changeName() throws AlienException;
    public default void shapeShift() {
        System.out.println("Shape shifting...");
    }
}
