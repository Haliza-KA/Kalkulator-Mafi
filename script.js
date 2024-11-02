function appendToDisplay(value) {
    const display = document.getElementById('display');
    if (value === '!') {
        const number = parseInt(display.value, 10);
        if (!isNaN(number)) {
            display.value = factorial(number);
        } else {
            alert('Masukkan angka valid untuk faktorial!');
        }
    } else {
        display.value += value;
    }
}

function clearDisplay() {
    document.getElementById('display').value = '';
}

function deleteLast() {
    const display = document.getElementById('display');
    display.value = display.value.slice(0, -1);
}

function calculate() {
    const display = document.getElementById('display');
    try {
        display.value = eval(display.value);
    } catch {
        alert('Ekspresi tidak valid');
    }
}

function factorial(n) {
    if (n < 0) return 'Error';
    if (n === 0 || n === 1) return 1;
    return n * factorial(n - 1);
}
