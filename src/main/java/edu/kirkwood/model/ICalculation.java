package edu.kirkwood.model;

public class ICalculation {
    private final double totalPrincipal;
    private final double stockRate;
    private final double bondRate;
    private final double totalInterest;

    public ICalculation(double totalPrincipal, double stockRate, double bondRate, double totalInterest) {
        this.totalPrincipal = totalPrincipal;
        this.stockRate = stockRate;
        this.bondRate = bondRate;
        this.totalInterest = totalInterest;

        if (stockRate == bondRate) {
            throw new IllegalArgumentException("Stock rate and bond rate cannot be equal for this calculation.");
        }
        if (totalPrincipal <= 0 || totalInterest < 0) {
            throw new IllegalArgumentException("Principal must be positive, and Interest cannot be negative.");
        }

/**
 * Solves the investment allocation problem:
 * x + y = total
 * x*rateA + y*rateB = interest
 *
 * Where:
 * x = Amount invested in stocks (Result[0])
 * y = Amount invested in bonds (Result[1])
 */

        double minRate = Math.min(stockRate, bondRate);
        double maxRate = Math.max(stockRate, bondRate);

        double minInterest = totalPrincipal * minRate;
        double maxInterest = totalPrincipal * maxRate;

        final double EPSILON = 0.0001;   //source form Gemini

        if (totalInterest < minInterest - EPSILON || totalInterest > maxInterest + EPSILON) {
            // This is the improved, specific error message
            throw new IllegalArgumentException(
                    String.format(
                            "The requested annual interest ($%.2f) is impossible to achieve with your rates. " +
                                    "The result must be between $%.2f and $%.2f.",
                            totalInterest, minInterest, maxInterest
                    )
            );
        }

    }

    public double[] solve() {
        double stockAllocation; // x
        double bondAllocation;  // y


        double numerator = totalInterest - (totalPrincipal * bondRate);
        double denominator = stockRate - bondRate;

        stockAllocation = numerator / denominator;
        bondAllocation = totalPrincipal - stockAllocation;


        if (Math.abs(stockAllocation + bondAllocation - totalPrincipal) > 0.01) {
            throw new IllegalArgumentException("Internal calculation error: Allocations do not sum up to the principal.");
        }

        return new double[]{stockAllocation, bondAllocation};
    }
}

