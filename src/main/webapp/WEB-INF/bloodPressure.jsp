<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Blood Pressure Calculator</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container my-4">
    <h1>Blood Pressure Calculator</h1>
    <p class="lead">Enter the systolic and diastolic pressures, then press submit to calculate the mean arterial
        pressure (MAP)</p>
    <form method="POST" action="bloodPressure">
        <div class="row">
            <div class="row g-2 align-items-center">
                <div class="col-2 align-items-center">
                    <label class="col-form-label">Systolic</label>
                </div>
                <div class="col-auto">
                    <label>
                        <input type="text" class="form-control" name="systolic" value="${systolic}">
                    </label>
                </div>
                <div class="col-auto">
                    <div class="text-danger">${systolicError}</div>
                </div>
            </div>
            <div class="row g-2 align-items-center">
                <div class="col-2">
                    <label class="col-form-label">Diastolic</label>
                </div>
                <div class="col-auto">
                    <label>
                        <input type="text" class="form-control" name="diastolic" value="${diastolic}">
                    </label>
                </div>
                <div class="col-auto">
                    <div class="text-danger">${diastolicError}</div>
                </div>
            </div>
        </div>
        <button type="submit" class="btn btn-primary mt-4 mb-4">Calculate MAP</button>
    </form>
    <div class="text-success fs-2">${result}</div>
    <div class="text-danger mt-2">${resultError}</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
