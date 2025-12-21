package edu.uoc.pac4.alien;

public class HumanoidAlien extends Alien {
    private int stamina;

    public HumanoidAlien(String name, int stamina) throws AlienException {
        super(name);
        setStamina(stamina);
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        if (stamina < 0) {
            stamina = 0;
        } else if (stamina > 100) {
            stamina = 100;
        }
        this.stamina = stamina;
    }

    public String toString() {
        return super.toString().replaceFirst("alienAttr",
                "\"stamina\": " + getStamina());
    }
}
