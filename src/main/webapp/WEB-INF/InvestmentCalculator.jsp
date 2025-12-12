<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Investment Calculator</title>
    <style>
        .error { color: red; font-size: 0.9em; margin-left: 10px; }
        .form-group { margin-bottom: 15px; }
        label { display: inline-block; width: 220px; font-weight: bold; }
        .result-box { border: 2px solid #0056b3; padding: 20px; background-color: #e6f0ff; margin-top: 25px; }
        h2 { color: #0056b3; }
    </style>
</head>
<body>
<h1>Investment Allocation Calculator</h1>
<p>Determine how a total investment (Principal) must be split between stocks and bonds to yield a specific annual interest income.</p>

<c:if test="${not empty errors.globalError}">
    <p class="error" style="font-size: 1.2em; border: 1px solid red; padding: 10px;">
        <strong>Error:</strong> ${errors.globalError}
    </p>
</c:if>

<form method="post" action="investment">

    <div class="form-group">
        <label for="totalAmount">Total Amount Invested ($):</label>
        <input type="text" id="totalAmount" name="totalAmount" value="${totalAmount}">
        <c:if test="${not empty errors.totalError}">
            <span class="error">${errors.totalError}</span>
        </c:if>
    </div>

    <div class="form-group">
        <label for="stockRate">Stock Interest Rate (%):</label>
        <input type="text" id="stockRate" name="stockRate" value="${stockRate}">
        <c:if test="${not empty errors.stockRateError}">
            <span class="error">${errors.stockRateError}</span>
        </c:if>
    </div>

    <div class="form-group">
        <label for="bondRate">Bond Interest Rate (%):</label>
        <input type="text" id="bondRate" name="bondRate" value="${bondRate}">
        <c:if test="${not empty errors.bondRateError}">
            <span class="error">${errors.bondRateError}</span>
        </c:if>
    </div>

    <div class="form-group">
        <label for="annualInterest">Total Annual Interest Income ($):</label>
        <input type="text" id="annualInterest" name="annualInterest" value="${annualInterest}">
        <c:if test="${not empty errors.interestError}">
            <span class="error">${errors.interestError}</span>
        </c:if>
    </div>

    <button type="submit">Calculate Allocation</button>
</form>

<c:if test="${not empty investedInStocks}">
    <div class="result-box">
        <h2>Investment Allocation Result</h2>
        <p>Based on the inputs, the principal should be allocated as follows:</p>
        <p><strong>Invested in Stocks:</strong> ${investedInStocks}</p>
        <p><strong>Invested in Bonds:</strong> ${investedInBonds}</p>
    </div>
</c:if>

</body>
</html>