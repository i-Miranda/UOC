package edu.uoc.pac4.alien;

public class ShadowAlien extends EtherealAlien implements ShapeShifter {
    private static final double TRANSPARENCY_LEVEL = 0.85;
    private int stealthLevel;

    public ShadowAlien(String name, int stealthLevel) throws AlienException {
        super(name, TRANSPARENCY_LEVEL);
        setStealthLevel(stealthLevel);
    }

    public int getStealthLevel() {
        return stealthLevel;
    }

    public void setStealthLevel(int stealthLevel) throws AlienException {
        if (stealthLevel < 0 || stealthLevel > 100) {
            throw new AlienException(AlienException.INVALID_STEALTH_LEVEL);
        }
        this.stealthLevel = stealthLevel;
    }

    @Override
    public void changeName() throws AlienException {
        shapeShift();
        setName(getName() + " (Shadow)");
    }

   public String toString(){
        return super.toString().replaceFirst("etherealAlienAttr",
                "\"stealthLevel\": " + getStealthLevel());
   }
}
