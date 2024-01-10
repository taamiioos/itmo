function loadHistory() {
    $.ajax({
        type: "GET",
        url: "../php/base.php",
        dataType: "json",
        success: function (data) {
            addInTable(data);
        },
        error: function (XHR, status, error) {
            console.error("Error:", error);
        }
    });
}

function addInTable(data) {
    tableBody.innerHTML = '';
    for (let i = 0; i < data.length; i++) {
        const row = document.createElement('tr');
        row.innerHTML = "<td>" + data[i]['x'] + "</td>" +
            "<td>" + data[i]['y'] + "</td>" +
            "<td>" + data[i]['r'] + "</td>" +
            "<td>" + data[i]['flag'] + "</td>" +
            "<td>" + data[i]['start_time'] + "</td>" +
            "<td>" + data[i]['time'] + "</td>";
        tableBody.appendChild(row);
    }
}

document.addEventListener("DOMContentLoaded", function () {
    loadHistory();
});
