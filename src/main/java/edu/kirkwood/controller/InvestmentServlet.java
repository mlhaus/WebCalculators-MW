package edu.kirkwood.controller;

import edu.kirkwood.model.ICalculation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "InvestmentServlet", urlPatterns = "/investment")
public class InvestmentServlet extends HttpServlet {

    // DoGet
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/InvestmentCalculator.jsp").forward(req, resp);
    }

    // DoPost
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Retrieve all parameters as Strings
        String totalStr = req.getParameter("totalAmount");
        String stockRateStr = req.getParameter("stockRate");
        String bondRateStr = req.getParameter("bondRate");
        String interestStr = req.getParameter("annualInterest");

        Map<String, String> errors = new HashMap<>();

        double total = 0;
        double stockRatePercent = 0; // Decimal value (e.g., 0.05)
        double bondRatePercent = 0;  // Decimal value (e.g., 0.08)
        double interest = 0;

        try {
            if (totalStr == null || totalStr.trim().isEmpty()) {
                errors.put("totalError", "Total Amount Invested is required.");
            } else {
                total = Double.parseDouble(totalStr);
                if (total <= 0) errors.put("totalError", "Total amount must be positive.");
            }
        } catch (NumberFormatException e) {
            errors.put("totalError", "Total Amount must be a valid number.");
        }

        // Validates the Stock Interest Rate
        try {
            if (stockRateStr == null || stockRateStr.trim().isEmpty()) {
                errors.put("stockRateError", "Stock Interest Rate is required.");
            } else {
                double stockRateRaw = Double.parseDouble(stockRateStr);
                if (stockRateRaw < 0) errors.put("stockRateError", "Stock rate cannot be negative.");
                stockRatePercent = stockRateRaw / 100.0;
            }
        } catch (NumberFormatException e) {
            errors.put("stockRateError", "Stock Rate must be a valid number.");
        }

        // Validate Bond Interest Rate
        try {
            if (bondRateStr == null || bondRateStr.trim().isEmpty()) {
                errors.put("bondRateError", "Bond Interest Rate is required.");
            } else {
                double bondRateRaw = Double.parseDouble(bondRateStr);
                if (bondRateRaw < 0) errors.put("bondRateError", "Bond rate cannot be negative.");
                // CONVERSION: Turn percentage (e.g., 10) into decimal (0.10)
                bondRatePercent = bondRateRaw / 100.0;
            }
        } catch (NumberFormatException e) {
            errors.put("bondRateError", "Bond Rate must be a valid number.");
        }

        // Validate Total Annual Interest Income
        try {
            if (interestStr == null || interestStr.trim().isEmpty()) {
                errors.put("interestError", "Total Annual Interest Income is required.");
            } else {
                interest = Double.parseDouble(interestStr);
                // Interest can technically be zero, but we prevent negative interest.
                if (interest < 0) errors.put("interestError", "Annual Interest cannot be negative.");
            }
        } catch (NumberFormatException e) {
            errors.put("interestError", "Annual Interest must be a valid number.");
        }


        // 3. Process Logic if No Errors
        if (errors.isEmpty()) {
            try {
                // Initialize the ICalculation model with validated, converted inputs
                ICalculation calc = new ICalculation(total, stockRatePercent, bondRatePercent, interest);

                // Solve the system of equations
                double[] result = calc.solve();

                double investedInStocks = result[0];
                double investedInBonds = result[1];

                // Set the results as request attributes for the JSP to display
                // The String.format ensures the output is nicely formatted to two decimal places
                req.setAttribute("investedInStocks", String.format("$%.2f", investedInStocks));
                req.setAttribute("investedInBonds", String.format("$%.2f", investedInBonds));
                req.setAttribute("calculationSuccessful", true);

            } catch (IllegalArgumentException e) {
                // Handle business logic exceptions from your Model (e.g., rates are equal,
                // impossible interest/principal combination resulting in negative allocation)
                errors.put("globalError", e.getMessage());
            } catch (Exception e) {
                // Catch unexpected errors and provide a generic message
                errors.put("globalError", "An unexpected error occurred during calculation.");
                e.printStackTrace(); // Log the full error for debugging
            }
        }

        // 4. Set Errors and Forward
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);

            // Re-set the original string input values so the user doesn't have to re-type them on the form
            req.setAttribute("totalAmount", totalStr);
            req.setAttribute("stockRate", stockRateStr);
            req.setAttribute("bondRate", bondRateStr);
            req.setAttribute("annualInterest", interestStr);
        }

        // Forward to the JSP page
        req.getRequestDispatcher("WEB-INF/InvestmentCalculator.jsp").forward(req, resp);
    }
}