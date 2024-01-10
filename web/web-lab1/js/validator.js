const y = document.getElementById('y');
const r = document.getElementById('r');
const alerts = document.getElementById('alerts');
const alertDiv = document.createElement('div');
const checkboxes = document.querySelectorAll('input[type="checkbox"]');
let x = null;

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('click', () => {
        if (checkbox.checked) {
            x = checkbox.value;
            checkboxes.forEach(otherCheckbox => {
                if (otherCheckbox !== checkbox) {
                    otherCheckbox.checked = false;
                }
            });
        } else {
            x = null;
        }
    });
});

function replaceDot(value) {
    return value.replace(',', '.');
}

const showAlert = message => {
    alertDiv.textContent = message;
    alerts.appendChild(alertDiv);
    setTimeout(() => {
        alertDiv.remove();
    }, 2000);
}

function validateXYR() {
    let yRep = replaceDot(y.value);
    let rRep = replaceDot(r.value);

    if (x === null) {
        showAlert('Выберите значение X!');
        return false;
    }

    if (yRep === "") {
        showAlert('Выберите значение для Y!');
        return false;
    }
    if (isNaN(yRep)) {
        showAlert('Значение Y должно быть числом!');
        return false;
    }
    if (yRep.includes('.') && yRep.split('.')[1].length > 2) {
        showAlert('Число Y должно быть не более двух знаков после запятой!');
        return false;
    }
    if (yRep < -5 || yRep > 3) {
        showAlert('Значение Y должно быть в диапазоне от -5 до 3');
        return false;
    }
    if (rRep === "") {
        showAlert('Выберите значение для R!');
        return false;
    }

    if (isNaN(rRep)) {
        showAlert('Значение R должно быть числом!');
        return false;
    }
    if (rRep.includes('.') && rRep.split('.')[1].length > 2) {
        showAlert('Число R должно быть не более двух знаков после запятой!');
        return false;
    }
    if (rRep < 1 || rRep > 4) {
        showAlert('Значение R должно быть в диапазоне от 1 до 4');
        return false;
    }
    return true;
}
