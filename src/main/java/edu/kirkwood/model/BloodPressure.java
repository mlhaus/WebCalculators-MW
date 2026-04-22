package edu.kirkwood.model;

/**
 * Represents a blood pressure with an integer systolic, diastolic,
 * and mean arterial pressure
 */
public class BloodPressure {

    private int systolic;
    private int diastolic;

    /**
     * Default constructor -
     * Initializes a new blood pressure object to 120/80
     */
    public BloodPressure() {
        this.systolic = 120;
        this.diastolic = 80;
    }

    /**
     * Parameterized constructor -
     * Initializes a new blood pressure with the specified systolic and diastolic parameters
     * @param systolic The top number of the blood pressure
     * @param diastolic The bottom number of the blood pressure
     */
    public BloodPressure(int systolic, int diastolic) {
        setSystolic(systolic);
        setDiastolic(diastolic);
    }

    /**
     * Gets systolic blood pressure
     * @return systolic The top portion of the blood pressure
     */
    public int getSystolic() {
        return systolic;
    }

    /**
     * Sets systolic blood pressure
     * @param systolic The top portion of the blood pressure
     */
    public void setSystolic(int systolic) {
        if (systolic < 0 || systolic == 0){
            throw new IllegalArgumentException("Systolic must be a positive integer.");
        }
        if (systolic > 400) {
            throw new IllegalArgumentException("Systolic must be less than 400.");
        }
        if (systolic < diastolic) {
            throw new IllegalArgumentException("Systolic must be higher than diastolic.");
        }
        if (systolic == diastolic) {
            throw new IllegalArgumentException("Systolic and diastolic cannot be equal.");
        }
        this.systolic = systolic;
    }

    /**
     * Gets diastolic blood pressure
     * @return diastolic The bottom portion of the blood pressure
     */
    public int getDiastolic() {
        return diastolic;
    }

    /**
     * Sets diastolic blood pressure
     * @param diastolic The bottom portion of the blood pressure
     */
    public void setDiastolic(int diastolic) {
        if (diastolic < 0 || diastolic == 0) {
            throw new IllegalArgumentException("Diastolic must be a positive integer.");
        }
        if (diastolic > 300) {
            throw new IllegalArgumentException("Diastolic must be lower than 300mmHg.");
        }
        if (diastolic > systolic) {
            throw new IllegalArgumentException("Systolic must be higher than diastolic.");
        }
        if (diastolic == systolic) {
            throw new IllegalArgumentException("Systolic and diastolic cannot be equal");
        }

        this.diastolic = diastolic;
    }

    /**
     * Calculates the mean arterial pressure a.k.a. "MAP"
     * @return meanArterialPressure The calculated MAP
     */
    public int calculateMAP() {
        return (this.systolic +2 * this.diastolic) / 3;
    }

    /**
     * Formats systolic and diastolic into a readable blood pressure
     * @return A blood pressure in the format "systolic/diastolic"
     */
    @Override
    public String toString() {
        return systolic + "/" + diastolic;
    }
}
