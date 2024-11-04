import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorSederhana extends JFrame implements ActionListener {
    private JTextField display;
    private String currentInput = "";
    private double num1 = 0;
    private String operator = "";

    public KalkulatorSederhana() {
        // Mengatur judul jendela
        setTitle("Kalkulator Sederhana");
        setSize(350, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(100, 100, 200)); // Warna latar belakang utama

        // Membuat label judul
        JLabel title = new JLabel("Kalkulator", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Padding
        add(title, BorderLayout.NORTH);

        // Menambahkan display
        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 22));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE); // Warna latar belakang display
        display.setForeground(Color.BLACK); // Warna teks display
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.CENTER);

        // Membuat panel tombol
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(100, 100, 200)); // Background panel

        // Tombol-tombol kalkulator
        String[] buttons = {
            "C", "DEL", "!", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", ""
        };

        for (String text : buttons) {
            if (!text.equals("")) {
                JButton button = new JButton(text);
                button.setFont(new Font("Segoe UI", Font.BOLD, 18));
                button.setBackground(new Color(230, 150, 80)); // Warna tombol seperti gambar
                button.setForeground(Color.BLACK); // Warna teks tombol
                button.setFocusPainted(false); // Menghilangkan garis fokus
                button.addActionListener(this);
                button.setBorder(BorderFactory.createLineBorder(new Color(150, 100, 50))); // Border tombol
                panel.add(button);
            }
        }

        // Menambahkan panel tombol ke jendela
        add(panel, BorderLayout.SOUTH);

        // Menampilkan jendela
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Jika tombol angka atau titik ditekan
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            currentInput += command;
            display.setText(currentInput);
        }
        // Jika tombol operator ditekan
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            if (!currentInput.isEmpty()) {
                num1 = Double.parseDouble(currentInput);
                operator = command;
                currentInput = "";
            }
        }
        // Jika tombol "=" ditekan
        else if (command.equals("=")) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double num2 = Double.parseDouble(currentInput);
                double result = 0;

                // Operasi matematika
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }

                display.setText(String.valueOf(result));
                currentInput = ""; // Reset input saat ini
                operator = ""; // Reset operator
            }
        }
        // Jika tombol "C" ditekan (Clear)
        else if (command.equals("C")) {
            currentInput = "";
            num1 = 0;
            operator = "";
            display.setText("");
        }
        // Jika tombol "DEL" ditekan (Hapus karakter terakhir)
        else if (command.equals("DEL")) {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                display.setText(currentInput);
            }
        }
        // Jika tombol "!" ditekan (Faktorial)
        else if (command.equals("!")) {
            if (!currentInput.isEmpty()) {
                int num = Integer.parseInt(currentInput);
                int factorial = 1;
                for (int i = 1; i <= num; i++) {
                    factorial *= i;
                }
                display.setText(String.valueOf(factorial));
                currentInput = ""; // Reset input saat ini
            }
        }
    }

    public static void main(String[] args) {
        // Menjalankan kalkulator
        new KalkulatorSederhana();
    }
}
