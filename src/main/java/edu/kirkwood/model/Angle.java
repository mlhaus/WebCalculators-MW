//ZeeKonCal
//9/27/2025-10/1/2025
//An angle object class for the unitCircleCalculator class
package edu.kirkwood.model;

import edu.kirkwood.shared.Helpers;

public class Angle {
    private double angle;
    private int quadrant;

    public Angle(double angle) {
        this.angle = angle;
    }

    /**
     * Gets the degrees of the angle.
     *
     * @return degrees of the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets the degrees of the angle.
     *
     * @param angle degrees of the new angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }


    /**
     * Gets the quadrant of the angle.
     *
     * @return an int between 1 and 4 (inclusive)
     */
    public int getQuadrant() {
        return quadrant;
    }

    /**
     * Sets the quadrant of the angle.
     */
    public void setQuadrant() {
        if(angle >= 0 && angle < 90) this.quadrant = 1;
        else if(angle >= 90 && angle < 180) this.quadrant = 2;
        else if(angle >= 180 && angle < 270) this.quadrant = 3;
        else if(angle >= 270 && angle < 360) this.quadrant = 4;
        else throw new IllegalArgumentException("Angle not in range [0, 360)");
    }

    /**
     * Puts the calling angle in proper degree form:
     * A double value between 0 (inclusive) and 360 (exclusive)
     * @param isDegrees true for degrees, false for radians
     */
    public void correctAngle(boolean isDegrees) {
        if (!isDegrees) {
            this.setAngle(Math.toDegrees(angle));
        }
        angle = angle % 360;
        if (angle < 0) {
            angle += 360;
        }
        this.setAngle(angle);
        this.setQuadrant();
    }

    /**
     * Calculates the sine value of an angle in decimal form to 3 digits. If
     * value is one of 17 basic angles, returns exact value instead.
     *
     * @return a String
     */
    public String calcSin() {
        String returnedString;
        switch ((int) Math.round(angle)) {
            case 30, 150, 210, 330 -> {
                returnedString = "1/2";
            }
            case 45, 135, 225, 315 -> {
                returnedString = "Root(2)/2";
            }
            case 60, 120, 240, 3 -> {
                returnedString = "Root(3)/2";
            }
            case 0, 180, 360 -> {
                return "0";
            }
            case 90, 270 -> {
                returnedString = "1";
            }
            default -> {
                return Helpers.round(Math.sin(Math.toRadians(angle)), 5);
            }
        }
        if(this.getQuadrant() == 3 || this.getQuadrant() == 4) {
            return "-" + returnedString;
        }
        return returnedString;
    }

    /**
     * Calculates the cosine value of an angle in decimal form to 3 digits. If
     * value is one of 17 basic angles, returns exact value instead.
     *
     * @return a String
     */
    public String calcCos() {
        String returnedString;
        switch ((int) Math.round(angle)) {
            case 30, 150, 210, 330 -> {
                returnedString = "Root(3)/2";
            }
            case 45, 135, 225, 315 -> {
                returnedString = "Root(2)/2";
            }
            case 60, 120, 240, 3 -> {
                returnedString = "1/2";
            }
            case 0, 180, 360 -> {
                returnedString = "1";
            }
            case 90, 270 -> {
                return "0";
            }
            default -> {
                return Helpers.round(Math.cos(Math.toRadians(angle)), 5);
            }
        }
        if(this.getQuadrant() == 2 || this.getQuadrant() == 3) {
            return "-" + returnedString;
        }
        return returnedString;
    }

    public static String formatAngleDetails(Angle angle, boolean isDegrees) {
        String formattedAngle = String.format("%s %s", Helpers.round(isDegrees? angle.getAngle() : Math.toRadians(angle.getAngle()), 3), (isDegrees ? "degrees" : "radians"));
        return String.format("The sin of %s is %s\nThe cos of %s is %s\nYour angle is in quadrant %s.\n\n", formattedAngle,
                angle.calcSin(), formattedAngle, angle.calcCos(), angle.getQuadrant());
    }
}
