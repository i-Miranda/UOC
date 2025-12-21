package edu.uoc.pac4.alien;

public abstract class EtherealAlien extends Alien {
   private double transparencyLevel;

   protected EtherealAlien(String name, double transparencyLevel) throws AlienException {
       super(name);
       setTransparencyLevel(transparencyLevel);
   }

   public double getTransparencyLevel() {
       return transparencyLevel;
   }

   public void setTransparencyLevel(double transparencyLevel) {
       if (transparencyLevel < 0.0) {
           transparencyLevel = 0.0;
       } else if (transparencyLevel > 1.0) {
           transparencyLevel = 1.0;
       }
       this.transparencyLevel = transparencyLevel;
   }

   public String toString() {
       int commaIndex = super.toString().indexOf(',') + 1;
       return super.toString().substring(0,commaIndex) +
               " \"etherealAlienDetails\": {" +
               super.toString().substring(commaIndex).replaceFirst("alienAttr",
                       "\"transparencyLevel\": " +
                               String.format("%.2f", getTransparencyLevel())) +
               ", etherealAlienAttr }";
   }
}
