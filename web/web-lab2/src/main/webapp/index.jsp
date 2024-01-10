<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c2" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <title>LabWork</title>
    <meta name="author" content="Татьяна Митина"/>
    <meta charset="UTF-8">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/buttons.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/table.css">
    <link rel="shortcut icon" href="ico/animal.ico" type="image/x-icon"/>
</head>
<body>
<div class="description">
    by Mitina Tatiana Olegovna | group: P3221 | variant: 2145
</div>

<div class="window">
    <svg id="coordinate_plane" width="400" height="400">
        <line x1="0" y1="200" x2="400" y2="200" stroke="black" stroke-width="2"></line>
        <line x1="200" y1="0" x2="200" y2="400" stroke="black" stroke-width="2"></line>

        <polygon points="400,200 390,195 390,205" fill="black"></polygon>
        <polygon points="200,0 195,10 205,10" fill="black"></polygon>

        <line x1="80" y1="195" x2="80" y2="205" stroke="black" stroke-width="2"></line>
        <line x1="140" y1="195" x2="140" y2="205" stroke="black" stroke-width="2"></line>
        <line x1="260" y1="195" x2="260" y2="205" stroke="black" stroke-width="2"></line>
        <line x1="320" y1="195" x2="320" y2="205" stroke="black" stroke-width="2"></line>

        <text id="Rx" x="75" y="220" font-size="12">-R</text>
        <text id="R/2x" x="135" y="220" font-size="12">-R/2</text>
        <text id="-R/2x" x="255" y="220" font-size="12">R/2</text>
        <text id="-Rx" x="315" y="220" font-size="12">R</text>

        <line x1="195" y1="80" x2="205" y2="80" stroke="black" stroke-width="2"></line>
        <line x1="195" y1="140" x2="205" y2="140" stroke="black" stroke-width="2"></line>
        <line x1="195" y1="260" x2="205" y2="260" stroke="black" stroke-width="2"></line>
        <line x1="195" y1="320" x2="205" y2="320" stroke="black" stroke-width="2"></line>

        <text id="Ry" x="180" y="85" font-size="12">R</text>
        <text id="R/2y" x="180" y="145" font-size="12">R/2</text>
        <text id="-R/2y" x="180" y="265" font-size="12">-R/2</text>
        <text id="-Ry" x="180" y="325" font-size="12">-R</text>

        <path d="M200,200 L200,320 A140,140 0 0,1 80,200 Z" fill="rgba(225, 217, 206, 0.5)" stroke="black"
              stroke-width="2"></path>
        <polygon points="80,200 200,200 200,80" fill="rgba(225, 217, 206, 0.5)" stroke="black"
                 stroke-width="2"></polygon>
        <polygon points="200,260 200,200 320,200 320,260" fill="rgba(225, 217, 206, 0.5)" stroke="black"
                 stroke-width="2"></polygon>
    </svg>

    <div class="inputFields">
        <div class="xCheckbox">
            <label>X:</label>
            <input type="checkbox" id="-5" name="x" value="-5">
            <label for="-5">-5</label>

            <input type="checkbox" id="-4" name="x" value="-4">
            <label for="-4">-4</label>

            <input type="checkbox" id="-3" name="x" value="-3">
            <label for="-3">-3</label>

            <input type="checkbox" id="-2" name="x" value="-2">
            <label for="-2">-2</label>

            <input type="checkbox" id="-1" name="x" value="-1">
            <label for="-1">-1</label>

            <input type="checkbox" id="0" name="x" value="0">
            <label for="0">0</label>

            <input type="checkbox" id="1" name="x" value="1">
            <label for="1">1</label>

            <input type="checkbox" id="2" name="x" value="2">
            <label for="2">2</label>

            <input type="checkbox" id="3" name="x" value="3">
            <label for="3">3</label>
        </div>

        <div class="yInput">
            <label for="y">Y:</label>
            <input style="width: 50px" type="number" id="y" name="y" min="-3" max="5" step="0.5" placeholder="-3..5">
        </div>

        <div class="chooseButtons">
            <label>R:</label>
            <button type="button" id="1.0" name="r" value="1">1</button>
            <button type="button" id="1.5" name="r" value="1.5">1.5</button>
            <button type="button" id="2.0" name="r" value="2">2</button>
            <button type="button" id="2.5" name="r" value="2.5">2.5</button>
            <button type="button" id="3.0" name="r" value="3">3</button>
        </div>
    </div>
    <div id="alerts"></div>
    <div class="mainButtons">
        <button type="button" id="sendButton">Send</button>
        <button type="button" id="resetButton">Reset</button>
    </div>
</div>

<table>
    <thead>
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>Flag</th>
        <th>Time</th>
        <th>Script Time(ms)</th>
    </tr>
    </thead>
    <tbody id="sessionData">
    </tbody>
</table>

</body>
<script src="js/buttonHandler.js"></script>
<script src="js/validator.js"></script>
<script src="js/point.js"></script>
<script src="js/table.js"></script>
</html>