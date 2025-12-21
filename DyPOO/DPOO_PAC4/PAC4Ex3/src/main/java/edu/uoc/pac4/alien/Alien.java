package edu.uoc.pac4.alien;

public abstract class Alien {
    private String name;

    protected Alien(String name) throws AlienException{
        setName(name);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) throws AlienException {
        if (name == null || name.isBlank()) {
            throw new AlienException(AlienException.INVALID_ALIEN_NAME);
        }
        this.name = name;
    }

    public String toString() {
        return "{ \"type\": \""+ getClass().getSimpleName() +
                "\", \"alienDetails\": { \"name\": \"" + getName() +
                "\" }, alienAttr }";
    }
}
