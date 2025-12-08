package edu.kirkwood.controller;

import edu.kirkwood.model.Angle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.kirkwood.model.Angle.formatAngleDetails;
import static edu.kirkwood.shared.Helpers.isValidInteger;
import static edu.kirkwood.shared.Helpers.isValidNumber;

@WebServlet(value="/unitCircle")
public class UnitCircleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/unitCircle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Step 1: Get parameters
        String angleMeasure = req.getParameter("angleMeasure");
        String unit =  req.getParameter("unit");
        // Step 2: Set parameters as attributes on the request object
        req.setAttribute("angleMeasure",angleMeasure);
        req.setAttribute("unit",unit);
        // Step 4: Validate the data
        boolean errorsFound = false;
        if(angleMeasure == null || angleMeasure.isEmpty()) {
            errorsFound = true;
            req.setAttribute("angleMeasureError", "<li>Angle measure is required</li>");
        } else if(!isValidNumber(angleMeasure)) {
            errorsFound = true;
            req.setAttribute("angleMeasureError", "<li>Angle measure is not a valid integer</li>");
        }
        if(unit == null || !unit.matches("(degrees|radians)")) {
            errorsFound = true;
            req.setAttribute("unitError", "<li>Unit is invalid</li>");
        }
        // Step 5: Produce the result
        if(!errorsFound) {
            Angle angle = new Angle(Double.parseDouble(angleMeasure));
            boolean isDegrees = false;
            if(unit.equals("degrees")) {
                isDegrees = true;
            }
            angle.correctAngle(isDegrees);
            String result = formatAngleDetails(angle, isDegrees);
            req.setAttribute("result", result);
        }
        // Step 3: Forward the request and response objects to the JSP
        req.getRequestDispatcher("WEB-INF/unitCircle.jsp").forward(req, resp);
    }
}
