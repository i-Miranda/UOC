package edu.uoc.pac4.alien;

public class VoidMorphAlien extends EtherealAlien implements ShapeShifter {
    private static final double TRANSPARENCY_LEVEL = 0.9;
    private double morphingAbility;

    public VoidMorphAlien(String name, double morphingAbility) throws AlienException {
        super(name, TRANSPARENCY_LEVEL);
        setMorphingAbility(morphingAbility);
    }

    public double getMorphingAbility() {
        return morphingAbility;
    }

    public void setMorphingAbility(double morphingAbility) throws AlienException {
        if (morphingAbility < 0.0 || morphingAbility > 1.0) {
            throw new AlienException(AlienException.INVALID_MORPHING_ABILITY);
        }
        this.morphingAbility = morphingAbility;
    }

    public void changeName() throws AlienException {
        shapeShift();
        setName(getName() + " (VoidMorph)");
    }

    public String toString() {
        return super.toString().replaceFirst("etherealAlienAttr",
                "\"morphingAbility\": " +
                String.format("%.2f",getMorphingAbility()));
    }
}
