const tableBody = document.querySelector('#table tbody');
document.addEventListener("DOMContentLoaded", function () {
    const sendButton = document.getElementById('sendButton');
    const resetButton = document.getElementById('resetButton');

    sendButton.addEventListener('click', send);
    resetButton.addEventListener('click', reset);
});

function send() {
    if (validateXYR()) {
        console.log("SENDING");
        $.ajax({
            type: "POST",
            url: "../php/base.php",
            dataType: "json",
            data: {
                x: x,
                y: y.value,
                r: r.value,
                time: new Date().getTimezoneOffset()
            },
            success: function (data) {
                addInTable(data);
            },
            error: function (XHR, status, error) {
                console.error("Error:", error);
            }
        });
    } else {
        console.error("Validation failed");
    }
}

function reset() {
    tableBody.innerHTML = '';
    $.ajax({
        type: "POST",
        url: "../php/reset.php",
        success: function () {
            console.log("Data cleared.");
        },
        error: function (XHR, status, error) {
            console.error("Error:", error);
        }
    });
}

