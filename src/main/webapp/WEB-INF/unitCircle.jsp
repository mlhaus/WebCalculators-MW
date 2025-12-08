<%--
  User: ZeeKonCal
  Date: 12/7/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Unit Circle Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container my-4">
    <h1>Unit Circle Calculator</h1>
    <p class="lead">Enter an angle measure & a unit</p>
    <form method="POST" action="unitCircle">
        <div class="row">
            <div class="col-2">
                <input type="text" class="form-control" placeholder="Angle Measure" aria-label="Angle Measure" name="angleMeasure" value="${angleMeasure}">
            </div>
            <div class="col-2 d-flex justify-content-center align-items-center display-3">
                <select class="form-select form-select-lg" aria-label="Unit" name="unit" value="${unit}">
                    <option <c:if test="${unit == 'degrees'}">selected</c:if> value="degrees">Degrees</option>
                    <option <c:if test="${unit == 'radians'}">selected</c:if> value="radians">Radians</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-2">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
            <div class="col-2">
                <button type="button" class="btn btn-primary" style="background-color: lightgoldenrodyellow;">
                    <a href="${pageContext.request.contextPath}/">Main Menu</a>
                </button>
            </div>
        </div>
    </form>
    <ul class="text-danger">
        ${angleMeasureError}
        ${unitError}
    </ul>
    <div class="text-success fs-1">${result}</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>