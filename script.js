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
        display.value += value; // Menambahkan nilai ke display
    }
}

function clearDisplay() {
    document.getElementById('display').value = ''; // Menghapus tampilan
}

function deleteLast() {
    const display = document.getElementById('display');
    display.value = display.value.slice(0, -1); // Menghapus karakter terakhir
}

function calculate() {
    const display = document.getElementById('display');
    try {
        // Mengevaluasi ekspresi dengan aman
        display.value = evaluateExpression(display.value);
    } catch (error) {
        alert('Ekspresi tidak valid');
    }
}

function factorial(n) {
    if (n < 0) return 'Error'; // Faktorial tidak terdefinisi untuk angka negatif
    if (n === 0 || n === 1) return 1;
    return n * factorial(n - 1);
}

function evaluateExpression(expr) {
    // Menggunakan RegExp untuk membatasi karakter yang diizinkan
    const validChars = /^[0-9+\-*/().! ]*$/;
    if (!validChars.test(expr)) {
        throw new Error('Invalid characters');
    }
    
    // Evaluasi ekspresi dengan eval()
    // Perhatian: eval() berpotensi berbahaya jika tidak dikelola dengan benar, pastikan input aman
    return eval(expr);
}
