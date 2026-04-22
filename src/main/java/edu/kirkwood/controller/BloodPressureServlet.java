package edu.kirkwood.controller;

import edu.kirkwood.model.BloodPressure;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/bloodPressure")
public class BloodPressureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/bloodPressure.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get raw data
        String systolic = req.getParameter("systolic");
        String diastolic = req.getParameter("diastolic");

        // Set attributes
        req.setAttribute("systolic", systolic);
        req.setAttribute("diastolic", diastolic);

        // Validate the data
        boolean valid = true;

        if (systolic == null || systolic.isEmpty()) {
            req.setAttribute("systolicError", "Systolic is required");
            valid = false;
        } else if (!systolic.matches("^[1-9]\\d{0,2}$")) {
            req.setAttribute("systolicError", "Systolic must be a whole number");
            valid = false;
        } else if (Integer.parseInt(systolic) > 400) {
            req.setAttribute("systolicError", "Systolic must be 400 or less");
            valid = false;
        }

        if (diastolic == null || diastolic.isEmpty()) {
            req.setAttribute("diastolicError", "Diastolic is required");
            valid = false;
        }  else if (!diastolic.matches("^[1-9]\\d{0,2}$")) {
            req.setAttribute("diastolicError", "Diastolic must be a whole number");
            valid = false;
        } else if (Integer.parseInt(diastolic) > 300) {
            req.setAttribute("diastolicError", "Diastolic must be 300 or less");
            valid = false;
        }

        if (valid) {
            if (Integer.parseInt(systolic) == Integer.parseInt(diastolic)) {
                req.setAttribute("resultError", "Systolic and Diastolic cannot be equal");
                valid = false;
            } else if (Integer.parseInt(systolic) < Integer.parseInt(diastolic)) {
                req.setAttribute("systolicError", "Systolic cannot be less than diastolic");
                valid = false;
            }
        }
        // Further validate by constructing a Blood Pressure object
        BloodPressure bloodPressure = null;
        int map = 0;
        String mapAlert = "";
        if (valid) {
            try {
                bloodPressure = new BloodPressure(Integer.parseInt(systolic),Integer.parseInt(diastolic));
            } catch (Exception e) {
                req.setAttribute("resultError", e.getMessage());
                valid = false;
            }
            try {
                map = bloodPressure.calculateMAP();
            } catch (Exception e) {
                req.setAttribute("resultError", e.getMessage());
                valid = false;
            }
            if (map < 65) {
                mapAlert = "Alert: MAP Low";
            } else if (map > 100) {
                mapAlert = "Alert: MAP High";
            }
        }
        // Assemble result
        if (valid) {
            String result = bloodPressure + " (" + map + ") " + mapAlert;
            req.setAttribute("result", result);
        }
        // Fwd attributes to JSP
        req.getRequestDispatcher("WEB-INF/bloodPressure.jsp").forward(req,resp);
    }
}
